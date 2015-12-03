package cc.blynk.server.model.widgets;

import cc.blynk.server.model.HardwareBody;
import cc.blynk.server.model.Pin;
import cc.blynk.server.model.enums.PinType;

import static cc.blynk.common.utils.StringUtils.*;

/**
 * The Blynk Project.
 * Created by Dmitriy Dumanskiy.
 * Created on 02.11.15.
 */
public abstract class MultiPinWidget extends Widget {

    public Pin[] pins;

    @Override
    public void updateIfSame(HardwareBody body) {
        if (pins != null) {
            for (int i = 0; i < pins.length; i++) {
                if (pins[i].isSame(body.pin, body.type)) {
                    pins[i].value = (body.value.length > 1 ? body.value[i] : body.value[0]);
                }
            }
        }
    }

    @Override
    public boolean isSame(byte pinIn, PinType pinType) {
        if (pins != null) {
            for (Pin pin : pins) {
                if (pin.isSame(pinIn, pinType)) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public String getValue(byte pinIn, PinType pinType) {
        if (pins != null) {
            for (Pin pin : pins) {
                if (pin.isSame(pinIn, pinType)) {
                    return pin.value;
                }
            }
        }
        return null;
    }

    public String makeHardwareBodyMerge() {
        StringBuilder sb = new StringBuilder(OnePinWidget.makeHardwareBody(pins[0]));
        for (int i = 1; i < pins.length; i++) {
            sb.append(BODY_SEPARATOR).append(pins[i].value);
        }
        return sb.toString();
    }

}