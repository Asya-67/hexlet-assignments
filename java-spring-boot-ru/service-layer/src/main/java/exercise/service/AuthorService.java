package exercise.service;

import exercise.dto.AuthorCreateDTO;
import exercise.dto.AuthorDTO;
import exercise.dto.AuthorUpdateDTO;
import exercise.exception.ResourceNotFoundException;
import exercise.mapper.AuthorMapper;
import exercise.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.stream.Collectors;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class AuthorService {
    // BEGIN
        @Autowired
        private AuthorRepository authorRepository;

        @Autowired
        private AuthorMapper authorMapper;

        public List<AuthorDTO> getAll() {
            return authorRepository.findAll()
                    .stream()
                    .map(authorMapper::toDto)
                    .collect(Collectors.toList());
        }

        public AuthorDTO getById(Long id) {
            Author author = authorRepository.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException("Author not found"));
            return authorMapper.toDto(author);
        }

        public AuthorDTO create(AuthorCreateDTO dto) {
            Author author = authorMapper.fromCreateDto(dto);
            author = authorRepository.save(author);
            return authorMapper.toDto(author);
        }

        public AuthorDTO update(Long id, AuthorUpdateDTO dto) {
            Author author = authorRepository.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException("Author not found"));
            authorMapper.update(dto, author);
            author = authorRepository.save(author);
            return authorMapper.toDto(author);
        }

        public void delete(Long id) {
            if (!authorRepository.existsById(id)) {
                throw new ResourceNotFoundException("Author not found");
            }
            authorRepository.deleteById(id);
        }
    // END
}
