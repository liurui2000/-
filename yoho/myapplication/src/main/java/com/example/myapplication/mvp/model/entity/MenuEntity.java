package com.example.myapplication.mvp.model.entity;

import java.util.List;

public class MenuEntity extends BaseEntity {


    private List<ValuesBean> values;

    public List<ValuesBean> getValues() {
        return values;
    }

    public void setValues(List<ValuesBean> values) {
        this.values = values;
    }

    public static class ValuesBean {
        /**
         * menu_id : 1
         * menu_name : MAN
         */

        private String menu_id;
        private String menu_name;

        public String getMenu_id() {
            return menu_id;
        }

        public void setMenu_id(String menu_id) {
            this.menu_id = menu_id;
        }

        public String getMenu_name() {
            return menu_name;
        }

        public void setMenu_name(String menu_name) {
            this.menu_name = menu_name;
        }
    }
}
