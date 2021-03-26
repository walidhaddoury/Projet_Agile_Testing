# cd ../src/ && javac -d ../bin/ test/acceptance/Homepage*.java
# cd ../bin/ && java org.junit.runner.JUnitCore test.acceptance.HomepageTest

cd ../src/ && javac -d ../bin/ test/acceptance/Configurateur*.java
cd ../bin/ && java org.junit.runner.JUnitCore test.acceptance.ConfigurateurTest
