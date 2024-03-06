package com.rokucraft.rokuemotes.config.serializers;

import net.kyori.adventure.key.Key;
import net.kyori.adventure.sound.Sound;
import org.jspecify.annotations.NullMarked;
import org.jspecify.annotations.Nullable;
import org.spongepowered.configurate.ConfigurationNode;
import org.spongepowered.configurate.serialize.SerializationException;
import org.spongepowered.configurate.serialize.TypeSerializer;

import java.lang.reflect.Type;

@NullMarked
public class SoundSerializer implements TypeSerializer<Sound> {
    public static final SoundSerializer INSTANCE = new SoundSerializer();

    private static final String KEY = "key";
    private static final String VOLUME = "volume";
    private static final String PITCH = "pitch";

    @Override
    @SuppressWarnings("PatternValidation")
    public Sound deserialize(Type type, ConfigurationNode node) throws SerializationException {
        String key;
        float volume = 1;
        float pitch = 1;
        if (node.isMap()) {
            key = node.node(KEY).getString();
            volume = node.node(VOLUME).getFloat(volume);
            pitch = node.node(PITCH).getFloat(pitch);
        } else {
            key = node.getString();
        }
        if (key == null) throw new SerializationException("Key cannot be null");
        return Sound.sound(Key.key(key), Sound.Source.PLAYER, volume, pitch);
    }

    @Override
    public void serialize(Type type, @Nullable Sound obj, ConfigurationNode node) throws SerializationException {
        if (obj == null) {
            node.raw(null);
            return;
        }

        node.node(KEY).set(obj.name().value());
        node.node(VOLUME).set(obj.volume());
        node.node(PITCH).set(obj.pitch());
    }
}
