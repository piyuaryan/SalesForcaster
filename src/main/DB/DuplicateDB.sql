-- You can create a new database exactly as the previous database existed and then drop the old database when you're done. Use the mysqldump tool to create a .sql backup of the database via mysqldump orig_db > orig_db.sql or if you need to use a username and password then run mysqldump -u root -p orig_db > orig_db.sql. orig_db is the name of the database you want to "rename", root would be the user you're logging in as and orig_db.sql would be the file created containing the backup. Now create a new, empty database with the name you want for the database. For example, mysql -u root -p -e "create database new_db". Once that's done, then run mysql -u root -p new_db < orig_db.sql. new_db now exists as a perfect copy of orig_db. You can then drop the original database as you now have it existing in the new database with the database name you wanted.

-- The short, quick steps without all the above explanation are:

mysqldump -u root -p originl_database > original_database.sql
mysql -u root -p -e "create database my_new_database"
mysql -u root -p my_new_database < original_database.sql
mysql -u root -p -e "drop database original_database"