package net.gitcoder.api.bukkit.module.hologram;

import lombok.Getter;
import net.gitcoder.api.bukkit.module.entity.impl.FakeArmorStand;
import net.gitcoder.api.java.utility.CacheManager;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public final class HologramManager extends CacheManager<MoonHologram> {

    /**
     * Кеширование голограммы в мапу по ее имени.
     */
    public void cacheHologram(String hologramName, MoonHologram hologram) {
        cacheData(hologramName.toLowerCase(), hologram);
    }

    /**
     * Получение голограммы из кеша по ее имени.
     */
    public MoonHologram getCachedHologram(String hologramName) {
        return getCache(hologramName.toLowerCase());
    }

    /**
     * Создание голограммы без использования абстракции.
     *
     * Все действия можно проводить через специальный для этого
     * Applicable, что указан в аргументах.
     */
    public void createHologram(String hologramName, Location location, Consumer<MoonHologram> hologramApplicable) {
        MoonHologram hologram = createHologram(location);

        cacheHologram(hologramName, hologram);

        hologramApplicable.accept(hologram);
    }

    /**
     * Создание голограммы и ее получение.
     */
    public MoonHologram createHologram(Location location) {
        return new MoonHologramImpl(location);
    }


    @Getter
    public static class MoonHologramImpl implements MoonHologram {

        private Location location;

        public MoonHologramImpl(Location location) {
            this.location = location;
        }


        private final List<FakeArmorStand> entities = new ArrayList<>();
        private final List<String> lines = new ArrayList<>();

        private Consumer<Player> clickAction;

        private final double distance = 0.25D;


        @Override
        public List<String> getLines() {
            return lines;
        }

        @Override
        public int getLineCount() {
            return getLines().size();
        }

        @Override
        public String getLine(int index) {
            return lines.get(index);
        }

        @Override
        public void addLine(String line) {
            if (location == null || location.getWorld() == null) {
                return;
            }

            FakeArmorStand stand = new FakeArmorStand(location.clone().add(0, -(distance * lines.size()), 0));

            stand.setInvisible(true);
            stand.setCustomNameVisible(true);
            stand.setCustomName(line);

            stand.setClickAction(clickAction);

            Bukkit.getOnlinePlayers().forEach(stand::addReceiver);

            entities.add(stand);
            lines.add(line);
        }

        @Override
        public void modifyLine(int index, String line) {
            lines.set(index, line);

            refreshHologram();
        }

        @Override
        public void spawn() {
            Bukkit.getOnlinePlayers().forEach(this::addReceiver);
        }

        @Override
        public void addReceiver(Player player) {
            entities.forEach(fakeArmorStand -> fakeArmorStand.addReceiver(player));
        }

        @Override
        public void remove() {
            Bukkit.getOnlinePlayers().forEach(this::removeReceiver);

            entities.clear();
        }

        @Override
        public void removeReceiver(Player player) {
            entities.forEach(fakeArmorStand -> fakeArmorStand.removeReceiver(player));
        }

        @Override
        public void setLocation(Location location) {
            this.location = location;

            int count = 0;
            for (FakeArmorStand stand : entities) {
                stand.teleport(location.clone().add(0, -(distance * count), 0));

                count++;
            }
        }

        @Override
        public void setClickAction(Consumer<Player> clickAction) {
            this.clickAction = clickAction;

            entities.forEach(fakeArmorStand -> fakeArmorStand.setClickAction(clickAction));
        }

        @Override
        public void refreshHologram() {
            for (int i = 0; i < lines.size(); i++) {

                String line = lines.get(i);
                FakeArmorStand stand = entities.get(i);

                stand.setCustomName(line);
            }

            setLocation(location);
        }
    }

}
