@echo off
pushd %~dp0
call .\usage-one %* && ^
.\itunes-audiomap-metadata %1 ..\..\..\CPL\chimera25\CPL-short-start-from-10-sec.xml "%~2\Chimera25-audiomap-metadata" "vendor_id" ..\..\chimera25\audiomap.xml ..\..\chimera25\film-metadata.xml %3