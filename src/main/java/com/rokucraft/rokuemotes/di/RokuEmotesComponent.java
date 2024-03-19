package com.rokucraft.rokuemotes.di;

import com.rokucraft.rokuemotes.RokuEmotesPlugin;
import dagger.BindsInstance;
import dagger.Component;

@Component(modules = {RokuEmotesModule.class})
public interface RokuEmotesComponent {
    @Component.Builder
    interface Builder {
        @BindsInstance
        Builder plugin(RokuEmotesPlugin plugin);
        RokuEmotesComponent build();
    }
}
