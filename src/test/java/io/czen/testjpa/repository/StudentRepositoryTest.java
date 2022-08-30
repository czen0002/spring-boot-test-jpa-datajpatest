package io.czen.testjpa.repository;

import io.czen.testjpa.entity.StudentEntity;
import io.czen.testjpa.repostory.StudentRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;
import java.util.stream.StreamSupport;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.nullValue;

@DataJpaTest
@Sql("classpath:DDL_scripts.sql")
class StudentRepositoryTest {

    @Autowired
    StudentRepository studentRepository;

    @Test
    void injectedComponentsAreNotNull() {
        assertThat(studentRepository, notNullValue());
    }

    @Test
    void expectTwoStudents_whenFindAll() {
        List<StudentEntity> result = findAllStudentEntityList();

        assertThat(result.size(), is(equalTo(2)));
    }

    @Test
    void expectOneStudent_whenFindByExistedId() {
        StudentEntity result = findStudentEntityOrNullById(1L);

        assertThat(result.getFirstName(), is(equalTo("Tony")));
        assertThat(result.getLastName(), is(equalTo("Parker")));
    }

    @Test
    void expectNull_whenFindByNotExistedId() {
        StudentEntity result = findStudentEntityOrNullById(3L);

        assertThat(result, is(nullValue()));
    }

    @Test
    void expectCreateNewStudent_whenSaveNewStudent() {
        StudentEntity newStudentEntity = new StudentEntity();
        newStudentEntity.setFirstName("Diana");
        newStudentEntity.setLastName("Princess");
        studentRepository.save(newStudentEntity);
        StudentEntity result = findStudentEntityOrNullById(3L);

        assertThat(result.getFirstName(), is(equalTo("Diana")));
        assertThat(result.getLastName(), is(equalTo("Princess")));
    }

    @Test
    void expectUpdateStudent_whenFindStudentById() {
        StudentEntity studentEntity = findStudentEntityOrNullById(1L);
        studentEntity.setFirstName("Diana");
        studentEntity.setLastName("Princess");
        studentRepository.save(studentEntity);
        StudentEntity result = findStudentEntityOrNullById(1L);

        assertThat(result.getFirstName(), is(equalTo("Diana")));
        assertThat(result.getLastName(), is(equalTo("Princess")));
    }

    @Test
    void expectDeleteStudent_whenStudentExist() {
        studentRepository.deleteById(1L);
        List<StudentEntity> result = findAllStudentEntityList();

        assertThat(result.size(), is(equalTo(1)));
    }

    private StudentEntity findStudentEntityOrNullById(Long id) {
        return studentRepository.findById(id).orElse(null);
    }

    private List<StudentEntity> findAllStudentEntityList() {
        return StreamSupport
                .stream(studentRepository.findAll().spliterator(), false)
                .toList();
    }
}
