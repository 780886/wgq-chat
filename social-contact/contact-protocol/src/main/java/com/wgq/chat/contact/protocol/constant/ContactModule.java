package com.wgq.chat.contact.protocol.constant;

import com.sheep.protocol.ModuleSupport;

public class ContactModule  {

    public static final ModuleSupport CONTACT = new ModuleSupport() {
        @Override
        public String code() {
            return "01";
        }

        @Override
        public String name() {
            return "CONTACT";
        }
    };

    public static final ModuleSupport QUN = new ModuleSupport() {
        @Override
        public String code() {
            return "02";
        }

        @Override
        public String name() {
            return "QUN";
        }
    };
}
