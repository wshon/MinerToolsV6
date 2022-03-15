package MinerTools;

import MinerTools.core.*;
import arc.scene.ui.layout.*;
import mindustry.input.*;
import mindustry.ui.dialogs.SettingsMenuDialog.SettingsTable.*;

import static arc.Core.settings;
import static mindustry.Vars.*;

public class MinerVars{
    public static MUI mui;

    public static boolean desktop;

    public static float fontScale = 0.75f;
    public static float imgSize = iconSmall * fontScale;

    public static boolean enableUpdateConveyor;

    public static void init(){
        mui = new MUI();

        betterUiscaleSetting();

        desktop = control.input instanceof DesktopInput;

        mui.init();
    }

    public static void betterUiscaleSetting(){
        int[] lastUiScale = {settings.getInt("uiscale", 100)};
        int index = ui.settings.graphics.getSettings().indexOf(setting -> setting.name.equals("uiscale"));

        settings.put("uiscale", settings.getInt("_uiscale", 100));

        if(index != -1){
            ui.settings.graphics.getSettings().set(index, new SliderSetting("uiscale", 100, 25, 300, 1, s -> {
                //if the user changed their UI scale, but then put it back, don't consider it 'changed'
                settings.put("uiscalechanged", s != lastUiScale[0]);
                settings.put("_uiscale", s);
                return s + "%";
            }));
        }
        ui.settings.graphics.rebuild();

        Scl.setProduct(settings.getInt("_uiscale", 100) / 100f);
    }
}