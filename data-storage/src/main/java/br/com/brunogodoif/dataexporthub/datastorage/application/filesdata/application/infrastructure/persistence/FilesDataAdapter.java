package br.com.brunogodoif.dataexporthub.datastorage.application.filesdata.application.adapters;

import br.com.brunogodoif.dataexporthub.datastorage.application.filesdata.application.controllers.records.FileDataDTO;
import br.com.brunogodoif.dataexporthub.datastorage.application.filesdata.application.domain.entity.FileData;
import br.com.brunogodoif.dataexporthub.datastorage.application.filesdata.application.domain.usecase.FilesDataUseCase;
import br.com.brunogodoif.dataexporthub.datastorage.application.filesdata.application.infrastructure.persistence.FileDataRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class FilesDataAdapter implements FilesDataUseCase {

    private final FileDataRepository fileDataRepository;

    public FilesDataAdapter(FileDataRepository fileDataRepository) {
        this.fileDataRepository = fileDataRepository;
    }

    @Override
    public List<FileDataDTO> getAllFileData() {
        return fileDataRepository.findAll().stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public FileDataDTO createFileData(FileDataDTO fileDataDTO) {
        // Mapear o DTO para a entidade FileData
        FileData fileData = mapToEntity(fileDataDTO);

        // Salvando a entidade no repositório
        FileData savedFileData = fileDataRepository.save(fileData);

        // Mapear a entidade de volta para o DTO e retornar
        return mapToDTO(savedFileData);
    }

    @Override
    public FileDataDTO getFileData(String id) {
        // Recuperar a entidade do repositório
        FileData fileData = fileDataRepository.findById(id).orElse(null);

        // Se não encontrou, retornar null
        if (fileData == null) {
            return null;
        }

        // Mapear a entidade para o DTO e retornar
        return mapToDTO(fileData);
    }

    @Override
    public FileDataDTO updateFileData(String id, FileDataDTO fileDataDTO) {
        // Verificar se o FileData existe
        if (!fileDataRepository.existsById(id)) {
            return null;
        }

        // Mapear o DTO para a entidade FileData
        FileData fileData = mapToEntity(fileDataDTO);
        fileData.setId(UUID.fromString(id)); // Definir o ID

        // Atualizar a entidade no repositório
        FileData updatedFileData = fileDataRepository.save(fileData);

        // Mapear a entidade de volta para o DTO e retornar
        return mapToDTO(updatedFileData);
    }

    @Override
    public boolean deleteFileData(String id) {
        // Verificar se o FileData existe
        if (!fileDataRepository.existsById(id)) {
            return false;
        }

        // Excluir o FileData do repositório
        fileDataRepository.deleteById(id);
        return true;
    }

    // Método para mapear FileDataDTO para FileData
    private FileData mapToEntity(FileDataDTO dto) {
        FileData entity = new FileData();
        entity.setId(UUID.fromString(dto.getId()));
        entity.setBucket(dto.getBucket());
        entity.setFileName(dto.getFileName());
        entity.setSize(dto.getSize());
        entity.setCreatedAt(dto.getCreatedAt());
        entity.setUpdatedAt(dto.getUpdatedAt());
        return entity;
    }
    // Método para mapear FileData para FileDataDTO
    private FileDataDTO mapToDTO(FileData entity) {
        FileDataDTO dto = new FileDataDTO();
        dto.setId(String.valueOf(entity.getId()));
        dto.setBucket(entity.getBucket());
        dto.setFileName(entity.getFileName());
        dto.setSize(entity.getSize());
        dto.setCreatedAt(entity.getCreatedAt());
        dto.setUpdatedAt(entity.getUpdatedAt());
        return dto;
    }
}