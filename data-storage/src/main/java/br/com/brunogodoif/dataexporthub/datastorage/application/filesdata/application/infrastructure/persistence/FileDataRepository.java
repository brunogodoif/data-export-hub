package br.com.brunogodoif.dataexporthub.datastorage.application.filesdata.application.infrastructure.persistence.mongodb;


import br.com.brunogodoif.dataexporthub.datastorage.application.filesdata.application.domain.entity.FileData;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface FileDataRepository extends MongoRepository<FileData, String> {
}