@echo off
set CLASSPATH=.
for %%i in (lib/*.jar) do call cpappend.bat %%i
set CLASSPATH=%CLASSPATH%
start javaw -cp "%CLASSPATH%" com.bhz.educard.App
exit
