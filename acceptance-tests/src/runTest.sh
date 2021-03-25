cd ../src/ && javac -d ../bin/ test/acceptance/Homepage*.java
cd ../bin/ && java org.junit.runner.JUnitCore test.acceptance.HomepageTest
