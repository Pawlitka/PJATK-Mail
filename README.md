# PJATK Mail
PJATK Mail is a simple email client application built in Java (Maven) using Swing.
It was created as a university project and demonstrates working with GUIs, local file storage, and sending emails via an external library.

## Features:

- Real-time clock displayed on the home window (updates every second).

- #### Contact management:

    - Add new contacts with Name, Surname, and Email.

    - Built-in email validation (must contain @ and .).

    - Contacts are stored in a local .csv file.

- #### Send emails:

    - Choose a recipient from your contact list.

    - Enter subject and message content.

    - Emails are sent via the Simple Java Mail library.

    - Sent emails are stored in a .csv file.

- #### History view:

    - On the home page, you can see a list of sent emails, including recipients, subjects, and message contents.

## Screenshots

### Home page
![Home Window](src/main/java/org/example/assets/home-view.png)

### Create Contact
![Create Contact](src/main/java/org/example/assets/create-contact.png)
![Create Contact](src/main/java/org/example/assets/craete_contact_correct.gif)
#### Invalid Contact Email
![Invalid contact email](src/main/java/org/example/assets/create_contact_invalid_email.gif)

### Send Email
![Create Email](src/main/java/org/example/assets/create-email.png)
![Create Email](src/main/java/org/example/assets/create_email.gif)

### Sent Email
![Sent Email](src/main/java/org/example/assets/sent-email.png)

## Future Improvements

- Migrate from CSV to a database for more robust data management
- Show replies to messages inside the app