package org.mossmc.mosscg.HyperVBox;

import org.mossmc.mosscg.HyperVBox.Cache.CacheVM;
import org.mossmc.mosscg.HyperVBox.PowerShell.PSGetVM;
import org.mossmc.mosscg.MossLib.Command.CommandManager;
import org.mossmc.mosscg.MossLib.Config.ConfigManager;
import org.mossmc.mosscg.MossLib.File.FileCheck;
import org.mossmc.mosscg.MossLib.Object.ObjectLogger;

public class Main {
    public static void main(String[] args) {
        //预加载部分
        long start = System.currentTimeMillis();
        FileCheck.checkDirExist("./HyperVBox");
        ObjectLogger logger = new ObjectLogger("./HyperVBox/logs");
        BasicInfo.logger = logger;

        //基础信息输出
        logger.sendInfo("欢迎使用HyperVBox软件");
        logger.sendInfo("软件版本：" + BasicInfo.version);
        logger.sendInfo("软件作者：" + BasicInfo.author);
        logger.sendWarn("请注意，运行本软件需要管理员权限，请以管理员权限运行！");

        //配置读取
        logger.sendInfo("正在读取配置文件......");
        BasicInfo.config = ConfigManager.getConfigObject("./HyperVBox", "config.yml", "config.yml");
        BasicInfo.debug = BasicInfo.config.getBoolean("debug");

        //初始化
        logger.sendInfo("正在初始化......");
        BasicInfo.runtime = Runtime.getRuntime();
        CacheVM.VMData = PSGetVM.execute();

        //命令行
        CommandManager.initCommand(logger,true);

        //启动完成信息
        long complete = System.currentTimeMillis();
        logger.sendInfo("启动完成！共耗时"+(complete-start)+"毫秒！");
    }
}
