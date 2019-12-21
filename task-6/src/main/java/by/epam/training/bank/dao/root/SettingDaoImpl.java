package by.epam.training.bank.dao.root;

import by.epam.training.bank.dao.BaseJsonDao;
import by.epam.training.bank.entity.config.AppConfig;
import by.epam.training.bank.entity.config.Settings;

import static by.epam.training.bank.constant.AppConstants.SETTINGS_PATTERN;

public class SettingDaoImpl extends BaseJsonDao<AppConfig> {

    @Override
    public Class<AppConfig> getRootObjectClass() {
        return AppConfig.class;
    }

    @Override
    protected String getFileName() {
        return SETTINGS_PATTERN;
    }

    public Settings getSettings() {
        return getRootObject().getSettings();
    }
}
