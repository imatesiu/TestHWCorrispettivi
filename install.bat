powershell -Command "(New-Object Net.WebClient).DownloadFile('https://ssec.isti.cnr.it/download/jdk-8u191-windows-x64.exe', 'jdk-8u191-windows-x64.exe')"

powershell -Command "Start-Process '.\jdk-8u191-windows-x64.exe' /s"

powershell -Command "(New-Object Net.WebClient).DownloadFile('https://ssec.isti.cnr.it/download/apache-maven-3.6.0-bin.zip', 'apache-maven-3.6.0-bin.zip')"

powershell -Command "Expand-Archive apache-maven-3.6.0-bin.zip"

powershell -Command "(New-Object Net.WebClient).DownloadFile('https://ssec.isti.cnr.it/download/Git-2.19.2-64-bit.exe', 'git_install.exe')"
powershell -Command "Start-Process '.\git_install.exe' '/S'"

powershell -Command "$theCurrentPath=(Get-ItemProperty -Path 'Registry::HKEY_LOCAL_MACHINE\System\CurrentControlSet\Control\Session Manager\Environment' -Name PATH).Path; $fullpath = (Get-Item -Path '.\').FullName; $fullpath += '\apache-maven-3.6.0-bin\apache-maven-3.6.0\bin'; $theUpdatedPath=$theCurrentPath+';C:\Program Files\Java\jdk1.8.0_191\jre\bin\;'+$fullpath; Set-ItemProperty -Path 'Registry::HKEY_LOCAL_MACHINE\System\CurrentControlSet\Control\Session Manager\Environment' -Name PATH –Value $theUpdatedPath; "

powershell -Command "Set-ItemProperty -Path 'Registry::HKEY_LOCAL_MACHINE\System\CurrentControlSet\Control\Session Manager\Environment' -Name JAVA_HOME -Value 'C:\Program Files\Java\jdk1.8.0_191\'"

powershell -Command "Set-ItemProperty -Path 'Registry::HKEY_LOCAL_MACHINE\System\CurrentControlSet\Control\Session Manager\Environment' -Name JRE_HOME -Value 'C:\Program Files\Java\jdk1.8.0_191\jre\'"

git clone https://github.com/imatesiu/TestHWCorrispettivi.git

cd TestHWCorrispettivi

