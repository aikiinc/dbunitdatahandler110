Preqrequisite Database Connection
---------------------------------------------
o Have your database connection up and running
o For MYSQL the connections is started externally.
  See http://dev.mysql.com/doc/refman/5.6/en/database-use.html
o For HSQL the hsqldbsetup/ directory has a sample database.
  The connection will be started automatically once test is run.
o The test/resources/hibernate.properties should be set to:
  hibernate.hbm2ddl.action=create
o Once the test is run, the schema will be cleaned and created automatically
o Notice that the pom.xml is configure to create a schema nameds testdb.
  <db.name>testdb</db.name>


Setup Test In Eclipse
--------------------------------
o Open Eclipse Run Configurations
o Right-clik Maven Build: New
o Name: bld
o Base directory: Brows Workspace, Select Project
o Goals, enter for MYSQL:
    clean install -DskipTests -P mysql
    -P mysql-> signify we will compile using MySQL DB
o Goals, enter for hsql:
    clean install -DskipTests -P hsql
    -P hsql-> signify we will compile using HSQL DB
o Click: Run
o Build should be: BUILD SUCESS


Run Test In Eclipse
--------------------------------
o Select the JDBCUtilTest
o Select test: getConnectionInfo()
o The schema will be created
o Test should run successfully


Set Default DB While Testing
------------------------------------
o In the pom.xml is the marker
  **** Set Default DB Here.
o Uncomment, the section and copy the desired profile properties
o This will help while testing from providing -P <profile> again and again



Run test from DOS Command Line
------------------------------------
mvn -P hsql -Dtest=DataSourceTest#getConnectionInfo test-compile test
mvn -P mysql -Dtest=DataSourceTest#getConnectionInfo test-compile test