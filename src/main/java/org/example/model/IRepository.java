package org.example.model;

import java.util.List;

public interface IRepository {
    void save(Contact contact) throws RepositoryException;

    List<Contact> load() throws RepositoryException;
}
