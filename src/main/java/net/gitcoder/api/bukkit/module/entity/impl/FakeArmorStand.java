package net.gitcoder.api.bukkit.module.entity.impl;

import com.comphenix.protocol.wrappers.WrappedDataWatcher;
import lombok.Getter;
import net.gitcoder.api.bukkit.module.entity.FakeEntity;
import org.bukkit.Location;
import org.bukkit.entity.EntityType;

public class FakeArmorStand extends FakeEntity {

    @Getter
    private boolean marker, small, basePlate, arms;

    public FakeArmorStand(Location location) {
        super(EntityType.ARMOR_STAND, location);
    }

    public void setSmall(boolean small) {
        this.small = small;

        getDataWatcher().setObject(new WrappedDataWatcher.WrappedDataWatcherObject(11, BYTE_SERIALIZER), generateBitMask());
        sendDataWatcherPacket();
    }

    public void setArms(boolean arms) {
        this.arms = arms;

        getDataWatcher().setObject(new WrappedDataWatcher.WrappedDataWatcherObject(11, BYTE_SERIALIZER), generateBitMask());
        sendDataWatcherPacket();
    }

    public void setBasePlate(boolean basePlate) {
        this.basePlate = basePlate;

        getDataWatcher().setObject(new WrappedDataWatcher.WrappedDataWatcherObject(11, BYTE_SERIALIZER), generateBitMask());
        sendDataWatcherPacket();
    }

    public void setMarker(boolean marker) {
        this.marker = marker;

        getDataWatcher().setObject(new WrappedDataWatcher.WrappedDataWatcherObject(11, BYTE_SERIALIZER), generateBitMask());
        sendDataWatcherPacket();
    }

    private byte generateBitMask() {
        return (byte) ((small ? 0x01 : 0) + (arms ? 0x04 : 0) + (!basePlate ? 0x08 : 0) + (marker ? 0x10 : 0));
    }

}
