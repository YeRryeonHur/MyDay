package com.example.myday1;

import java.util.ArrayList;

public class ListViewItem {

        public ListViewItem(String namestr, String timestr) {
            this.namestr = namestr;
            this.timestr = timestr;
        }

        private String namestr; //일정 이름
        private String timestr; //걸린 시간


        public void setName(String Name) {
            namestr = Name;
        }

        public void setTime(String Time) {
            timestr = Time;
        }

        public String getName() {
            return this.namestr;
        }

        public String getTime() {
            return this.timestr;
        }

}
