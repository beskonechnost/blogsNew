In order to get started with the application, you must first create a database.

Create a database - a blog with settings that are specified in the file or change them to your liking.

Then uncomment the last line in this file. This is necessary in order to add to the database of users and some messages.

Attention! 
After the first launch, the last line in the file must be commented out.

For testing, we have several users -
username - admin, password - admin;
username - user, password - user;
username - userTest, password - userTest;

They are all entitled to the same rights.
After logging in, you can start chatting in a blog. It is realized within the means REST API.
You can add new messages, edit and delete your old messages. You can select from the list only your messages.

Actually that's all. I would also add a paginator, but alas :-)