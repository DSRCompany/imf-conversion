@echo off
pushd %~dp0
call .\usage-one %* && ^
.\itunes-cc %1 ..\..\..\CPL\chimera25\CPL-short.xml "%~2\Chimera25-CC" "vendor_id" ..\..\chimera25\cc.scc %3