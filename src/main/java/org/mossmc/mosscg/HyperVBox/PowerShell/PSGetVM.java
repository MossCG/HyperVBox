package org.mossmc.mosscg.HyperVBox.PowerShell;

import com.alibaba.fastjson.JSONObject;
import org.mossmc.mosscg.HyperVBox.BasicInfo;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public class PSGetVM {
    public static JSONObject execute() {
        try {
            Process process = BasicInfo.runtime.exec("powershell.exe Get-VM");
            process.getOutputStream().write("\r\n".getBytes(StandardCharsets.UTF_8));
            process.getOutputStream().flush();
            process.getOutputStream().close();
            BufferedReader powershellOutput = new BufferedReader(new InputStreamReader(process.getInputStream()));

            JSONObject data = new JSONObject();
            String info;

            while ((info = powershellOutput.readLine()) != null) {
                if (info.length() < 1) continue;
                BasicInfo.sendDebug(info);
                if (info.startsWith("-") || info.startsWith("Name")) continue;

                String[] keys = info.split("\\s+");
                JSONObject dataVM = new JSONObject();
                dataVM.put("name",keys[0]);
                dataVM.put("state",keys[1]);
                dataVM.put("usageCPU",keys[2]);
                dataVM.put("usageMEM",keys[3]);
                dataVM.put("timeUP",keys[4]);
                dataVM.put("status",keys[5]);
                dataVM.put("version",keys[6]);
                data.put(keys[0],dataVM);
            }

            return data;
        } catch (Exception e) {
            BasicInfo.logger.sendException(e);
            return null;
        }
    }
}
