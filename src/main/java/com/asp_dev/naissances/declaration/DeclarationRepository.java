package com.asp_dev.naissances.declaration;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DeclarationRepository extends JpaRepository<Declaration, Integer> {
    @Query(value = "from Declaration d " +
            "join Profiles fp " +
            "on d.firstParent.id = fp.id " +
            "where fp.email = ?1 ")
    List<Declaration> findCurrentUserDeclarations(String email);
}
