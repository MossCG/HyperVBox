package org.mossmc.mosscg.HyperVBox.PowerShell;

import org.mossmc.mosscg.HyperVBox.BasicInfo;

import java.nio.charset.StandardCharsets;

public class PSStartVM {
    public static void execute(String name) {
        try {
            Process process = BasicInfo.runtime.exec("powershell.exe Start-VM -Name "+name);
            process.getOutputStream().write("\r\n".getBytes(StandardCharsets.UTF_8));
            process.getOutputStream().flush();
            process.getOutputStream().close();
        } catch (Exception e) {
            BasicInfo.logger.sendException(e);
        }
    }
}
