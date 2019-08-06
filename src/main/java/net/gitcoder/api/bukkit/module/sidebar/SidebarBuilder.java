package net.gitcoder.api.bukkit.module.sidebar;

import net.gitcoder.api.bukkit.module.sidebar.objective.SidebarObjective;

/**
 * @Author GitCoder.
 * <p>
 * The code is developed by the developer GitCoder.
 * You cannot change this code or interact with it in any way.
 * <p>
 * All right's is reserved.
 */
public class SidebarBuilder {

    public static SidebarBuilder newBuilder() {
        return new SidebarBuilder();
    }

    private Sidebar sidebar;

    public SidebarBuilder setObjective(String text) {
        sidebar.setObjective(new SidebarObjective(text, text));

        return this;
    }
}
