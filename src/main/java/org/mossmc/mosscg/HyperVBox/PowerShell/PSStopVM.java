package org.mossmc.mosscg.HyperVBox.PowerShell;

import org.mossmc.mosscg.HyperVBox.BasicInfo;

import java.nio.charset.StandardCharsets;

public class PSStopVM {
    public static void execute(String name) {
        try {
            Process process = BasicInfo.runtime.exec("powershell.exe Stop-VM -Name "+name);
            process.getOutputStream().write("\r\n \r\n".getBytes(StandardCharsets.UTF_8));
            process.getOutputStream().flush();
            process.getOutputStream().close();
        } catch (Exception e) {
            BasicInfo.logger.sendException(e);
        }
    }
}
