package org.example.model;

import java.util.ArrayList;
import java.util.List;

public interface IRepository {
    void saveContacts(Contact contact) throws RepositoryException;

    List<Contact> loadContacts() throws RepositoryException;

    void saveMessages(Message message) throws RepositoryException;

    List<Message> loadMessages() throws RepositoryException;
}
