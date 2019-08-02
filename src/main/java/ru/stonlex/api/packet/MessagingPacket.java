package ru.stonlex.api.packet;

import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import lombok.Getter;

/**
 * @Author ItzStonlex.
 * @VK https://vk.com/itzstonlex
 * <p>
 * (Created on 02.08.2019 18:55)
 */
@Getter
public abstract class MessagingPacket {

    private final int id;

    private final byte[] bytes;

    private final ByteArrayDataOutput dataOutput;


    /**
     * Создание, инициализация, чтение и
     *  отправка пакета на бомжу
     *
     * @param id - ID пакета
     */
    public MessagingPacket(int id) {
        this.id = id;

        dataOutput = ByteStreams.newDataOutput();
        dataOutput.writeInt(id);

        write(dataOutput);

        this.bytes = dataOutput.toByteArray();
    }

    /**
     * Запись и отправка пакета на BungeeCord
     *
     * @param dataOutput - OutputStream
     */
    public abstract void write(ByteArrayDataOutput dataOutput);

}
