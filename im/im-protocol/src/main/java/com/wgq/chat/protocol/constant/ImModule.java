package com.wgq.chat.protocol.constant;

import com.sheep.protocol.ModuleSupport;

public class ImModule {

    public static final ModuleSupport MESSAGE = new ModuleSupport() {
        @Override
        public String code() {
            return "01";
        }

        @Override
        public String name() {
            return "MESSAGE";
        }
    };

    public static final ModuleSupport ROOM = new ModuleSupport() {
        @Override
        public String code() {
            return "02";
        }

        @Override
        public String name() {
            return "ROOM";
        }
    };

}
