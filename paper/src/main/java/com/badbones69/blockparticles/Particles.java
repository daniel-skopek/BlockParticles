package com.badbones69.blockparticles;

import com.badbones69.blockparticles.api.ParticleControl;
import com.badbones69.blockparticles.api.enums.particles.CustomParticles;
import com.ryderbelserion.fusion.paper.scheduler.FoliaScheduler;
import io.papermc.paper.threadedregions.scheduler.ScheduledTask;
import org.bukkit.*;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

public class Particles implements ParticleControl {

    private final BlockParticles plugin = BlockParticles.getPlugin();
    private final Server server = plugin.getServer();
    
    private final Map<String, ScheduledTask> locations = new HashMap<>();
    private final int range = 25;
    
    private Location randomDrop(Location location) {
        double x = ThreadLocalRandom.current().nextInt(100) / 100.0 - .50;
        double z = ThreadLocalRandom.current().nextInt(100) / 100.0 - .50;
        return location.add(x, 0, z);
    }
    
    private float randomVector() {
        return (float) (-.05 + ThreadLocalRandom.current().nextDouble(.1));
    }

    @Override
    public Map<String, ScheduledTask> getLocations() {
        return locations;
    }

    public void playVolcano(final Location location, String id) {
        locations.put(id, new FoliaScheduler(this.plugin, location) {
            final Location clonedLocation = location.clone().add(.5, .8, .5);

            final World world = location.getWorld();

            @Override
            public void run() {
                if (noPlayers(location, range)) return;

                world.spawnParticle(Particle.LAVA, clonedLocation, 10, 0, 0, 0, 0);
            }
        }.runAtFixedRate(0, 4));
    }
    
    public void playBigFlame(final Location location, String id) {
        locations.put(id, new FoliaScheduler(this.plugin, location) {
            final Location l = location.clone().add(.5, .1, .5);

            final World world = location.getWorld();
            final Location[] circle1 = circle(l, 1, 15);
            final Location[] circle2 = circle(l, 2, 25);

            @Override
            public void run() {
                if (noPlayers(l, range)) return;
                for (Location location : circle1)
                    world.spawnParticle(Particle.FLAME, location, 1, 0, 0, 0, 0);
                for (Location location : circle2)
                    world.spawnParticle(Particle.FLAME, location, 1, 0, 0, 0, 0);
            }
        }.runAtFixedRate(0, 5));
    }
    
    public void playFlame(final Location location, String id) {
        locations.put(id, new FoliaScheduler(this.plugin, location) {
            final Location l = location.clone().add(.5, .1, .5);

            final World world = location.getWorld();
            final Location[] circle1 = circle(l, .6, 15);
            final Location[] circle2 = circle(l, 1, 20);

            @Override
            public void run() {
                if (noPlayers(l, range)) return;
                for (Location location : circle1)
                    world.spawnParticle(Particle.FLAME, location, 1, 0, 0, 0, 0);
                for (Location location : circle2)
                    world.spawnParticle(Particle.FLAME, location, 1, 0, 0, 0, 0);
            }
        }.runAtFixedRate(0, 5));
    }
    
    public void playDoubleSpiral(final Location location, String id, CustomParticles customParticles, int amount) {
        locations.put(id, new FoliaScheduler(this.plugin, location) {
            final Location l = location.add(.5, .7, .5);
            int time = 16;
            final Particle particle = customParticles == CustomParticles.DOUBLEWITCH ? Particle.WITCH : Particle.FIREWORK;

            final World world = location.getWorld();

            @Override
            public void run() {
                if (noPlayers(l.clone(), range)) return;

                switch (time) {
                    case 15 -> {
                        world.spawnParticle(particle, l.clone().add(.8, 0, 0), amount, 0, 0, 0, 0);
                        world.spawnParticle(particle, l.clone().add(-.8, 0, 0), amount, 0, 0, 0, 0);
                    }

                    case 14 -> {
                        world.spawnParticle(particle, l.clone().add(.75, 0, .43), amount, 0, 0, 0, 0);
                        world.spawnParticle(particle, l.clone().add(-.75, 0, -.43), amount, 0, 0, 0, 0);
                    }

                    case 13 -> {
                        world.spawnParticle(particle, l.clone().add(.65, 0, .65), amount, 0, 0, 0, 0);
                        world.spawnParticle(particle, l.clone().add(-.65, 0, -.65), amount, 0, 0, 0, 0);
                    }

                    case 12 -> {
                        world.spawnParticle(particle, l.clone().add(.43, 0, .75), amount, 0, 0, 0, 0);
                        world.spawnParticle(particle, l.clone().add(-.43, 0, -.75), amount, 0, 0, 0, 0);
                    }

                    case 11 -> {
                        world.spawnParticle(particle, l.clone().add(0, 0, .8), amount, 0, 0, 0, 0);
                        world.spawnParticle(particle, l.clone().add(0, 0, -.8), amount, 0, 0, 0, 0);
                    }

                    case 10 -> {
                        world.spawnParticle(particle, l.clone().add(-.43, 0, .75), amount, 0, 0, 0, 0);
                        world.spawnParticle(particle, l.clone().add(.43, 0, -.75), amount, 0, 0, 0, 0);
                    }

                    case 9 -> {
                        world.spawnParticle(particle, l.clone().add(-.65, 0, .65), amount, 0, 0, 0, 0);
                        world.spawnParticle(particle, l.clone().add(.65, 0, -.65), amount, 0, 0, 0, 0);
                    }

                    case 8 -> {
                        world.spawnParticle(particle, l.clone().add(-.75, 0, .43), amount, 0, 0, 0, 0);
                        world.spawnParticle(particle, l.clone().add(.75, 0, -.43), amount, 0, 0, 0, 0);
                    }

                    case 7 -> {
                        world.spawnParticle(particle, l.clone().add(-.8, 0, 0), amount, 0, 0, 0, 0);
                        world.spawnParticle(particle, l.clone().add(.8, 0, 0), amount, 0, 0, 0, 0);
                    }

                    case 6 -> {
                        world.spawnParticle(particle, l.clone().add(-.75, 0, -.43), amount, 0, 0, 0, 0);
                        world.spawnParticle(particle, l.clone().add(.75, 0, .43), amount, 0, 0, 0, 0);
                    }

                    case 5 -> {
                        world.spawnParticle(particle, l.clone().add(-.65, 0, -.65), amount, 0, 0, 0, 0);
                        world.spawnParticle(particle, l.clone().add(.65, 0, .65), amount, 0, 0, 0, 0);
                    }

                    case 4 -> {
                        world.spawnParticle(particle, l.clone().add(-.43, 0, -.75), amount, 0, 0, 0, 0);
                        world.spawnParticle(particle, l.clone().add(.43, 0, .75), amount, 0, 0, 0, 0);
                    }

                    case 3 -> {
                        world.spawnParticle(particle, l.clone().add(0, 0, -.8), amount, 0, 0, 0, 0);
                        world.spawnParticle(particle, l.clone().add(0, 0, .8), amount, 0, 0, 0, 0);
                    }

                    case 2 -> {
                        world.spawnParticle(particle, l.clone().add(.43, 0, -.75), amount, 0, 0, 0, 0);
                        world.spawnParticle(particle, l.clone().add(-.43, 0, .75), amount, 0, 0, 0, 0);
                    }

                    case 1 -> {
                        world.spawnParticle(particle, l.clone().add(.65, 0, -.65), amount, 0, 0, 0, 0);
                        world.spawnParticle(particle, l.clone().add(-.65, 0, .65), amount, 0, 0, 0, 0);
                    }

                    case 0 -> {
                        world.spawnParticle(particle, l.clone().add(.75, 0, -.43), amount, 0, 0, 0, 0);
                        world.spawnParticle(particle, l.clone().add(-.75, 0, .43), amount, 0, 0, 0, 0);
                        time = 16;
                    }
                }

                time--;
            }
        }.runAtFixedRate(0, 2));
    }
    
    public void playSpiral(final Location location, String id, CustomParticles customParticles, int amount) {
        locations.put(id, new FoliaScheduler(this.plugin, location) {
            final Location l = location.add(.5, .7, .5);
            int time = 16;
            final Particle particle = customParticles == CustomParticles.WITCH ? Particle.WITCH : Particle.FIREWORK;

            final World world = location.getWorld();

            @Override
            public void run() {
                if (noPlayers(l.clone(), range)) return;

                switch (time) {
                    case 15 -> world.spawnParticle(particle, l.clone().add(.8, 0, 0), amount, 0, 0, 0, 0);
                    case 14 -> world.spawnParticle(particle, l.clone().add(.75, 0, .43), amount, 0, 0, 0, 0);
                    case 13 -> world.spawnParticle(particle, l.clone().add(.65, 0, .65), amount, 0, 0, 0, 0);
                    case 12 -> world.spawnParticle(particle, l.clone().add(.43, 0, .75), amount, 0, 0, 0, 0);
                    case 11 -> world.spawnParticle(particle, l.clone().add(0, 0, .8), amount, 0, 0, 0, 0);
                    case 10 -> world.spawnParticle(particle, l.clone().add(-.43, 0, .75), amount, 0, 0, 0, 0);
                    case 9 -> world.spawnParticle(particle, l.clone().add(-.65, 0, .65), amount, 0, 0, 0, 0);
                    case 8 -> world.spawnParticle(particle, l.clone().add(-.75, 0, .43), amount, 0, 0, 0, 0);
                    case 7 -> world.spawnParticle(particle, l.clone().add(-.8, 0, 0), amount, 0, 0, 0, 0);
                    case 6 -> world.spawnParticle(particle, l.clone().add(-.75, 0, -.43), amount, 0, 0, 0, 0);
                    case 5 -> world.spawnParticle(particle, l.clone().add(-.65, 0, -.65), amount, 0, 0, 0, 0);
                    case 4 -> world.spawnParticle(particle, l.clone().add(-.43, 0, -.75), amount, 0, 0, 0, 0);
                    case 3 -> world.spawnParticle(particle, l.clone().add(0, 0, -.8), amount, 0, 0, 0, 0);
                    case 2 -> world.spawnParticle(particle, l.clone().add(.43, 0, -.75), amount, 0, 0, 0, 0);
                    case 1 -> world.spawnParticle(particle, l.clone().add(.65, 0, -.65), amount, 0, 0, 0, 0);
                    case 0 -> {
                        world.spawnParticle(particle, l.clone().add(.75, 0, -.43), amount, 0, 0, 0, 0);
                        time = 16;
                    }
                }

                time--;
            }
        }.runAtFixedRate(0, 2));
    }
    
    public void playCrit(final Location location, String id) {
        locations.put(id, new FoliaScheduler(this.plugin, location) {
            final Location l = location.add(.5, 1.1, .5);

            final World world = location.getWorld();

            @Override
            public void run() {
                if (noPlayers(l.clone(), range)) return;
                world.spawnParticle(Particle.CRIT, l.clone(), 1, 0, 0, 0, 0);
            }
        }.runAtFixedRate(0, 2));
    }
    
    public void playBigCrit(final Location location, String id) {
        locations.put(id, new FoliaScheduler(this.plugin, location) {
            final Location l = location.clone().add(.5, .5, .5);

            final World world = location.getWorld();
            final Location[] circle = circle(l, 2, 20);

            @Override
            public void run() {
                if (noPlayers(l, range)) return;
                for (Location location : circle)
                    world.spawnParticle(Particle.CRIT, location, 1, 0, 0, 0, 0);
            }
        }.runAtFixedRate(0, 2));
    }
    
    public void playStorm(final Location location, String id) {
        locations.put(id, new FoliaScheduler(this.plugin, location) {
            final Location l = location.add(.5, 2, .5);

            final World world = location.getWorld();

            @Override
            public void run() {
                if (noPlayers(l.clone(), range)) return;
                world.spawnParticle(Particle.CLOUD, l.clone(), 15, .3f, 0, 0.3f, 0);
                world.spawnParticle(Particle.FALLING_WATER, l.clone().add(0, 0, .1), 10, 0.2f, 0, 0.2f, 0);
            }
        }.runAtFixedRate(0, 2));
    }
    
    public void playFog(final Location location, String id) {
        locations.put(id, new FoliaScheduler(this.plugin, location) {
            final Location l = location.add(.5, .5, .5);

            final World world = location.getWorld();

            @Override
            public void run() {
                if (noPlayers(l.clone(), range)) return;
                world.spawnParticle(Particle.CLOUD, l, 20, .3f, 0, .3f, 0.05f);
            }
        }.runAtFixedRate(0, 2));
    }
    
    public void playEnchant(final Location location, String id) {
        locations.put(id, new FoliaScheduler(this.plugin, location) {
            final Location l = location.add(.5, 1.5, .5);

            final World world = location.getWorld();

            @Override
            public void run() {
                if (noPlayers(l.clone(), range)) return;
                world.spawnParticle(Particle.ENCHANT, l, 20, 0, 0, 0, 2);
            }
        }.runAtFixedRate(0, 2));
    }
    
    public void playChains(final Location location, String id) {
        locations.put(id, new FoliaScheduler(this.plugin, location) {
            final Location l = location.add(.5, .1, .5);

            final World world = location.getWorld();

            @Override
            public void run() {
                if (noPlayers(l.clone(), range)) return;
                world.spawnParticle(Particle.FLAME, l.clone().add(1, 0, 1), 1, 0, 0, 0, 0);
                world.spawnParticle(Particle.FLAME, l.clone().add(.9, .1, .9), 1, 0, 0, 0, 0);
                world.spawnParticle(Particle.FLAME, l.clone().add(.8, .2, .8), 1, 0, 0, 0, 0);
                world.spawnParticle(Particle.FLAME, l.clone().add(.7, .3, .7), 1, 0, 0, 0, 0);
                world.spawnParticle(Particle.FLAME, l.clone().add(.6, .4, .6), 1, 0, 0, 0, 0);
                world.spawnParticle(Particle.FLAME, l.clone().add(.5, .6, .5), 1, 0, 0, 0, 0);
                world.spawnParticle(Particle.FLAME, l.clone().add(.4, .8, .4), 1, 0, 0, 0, 0);

                world.spawnParticle(Particle.FLAME, l.clone().add(-1, 0, 1), 1, 0, 0, 0, 0);
                world.spawnParticle(Particle.FLAME, l.clone().add(-.9, .1, .9), 1, 0, 0, 0, 0);
                world.spawnParticle(Particle.FLAME, l.clone().add(-.8, .2, .8), 1, 0, 0, 0, 0);
                world.spawnParticle(Particle.FLAME, l.clone().add(-.7, .3, .7), 1, 0, 0, 0, 0);
                world.spawnParticle(Particle.FLAME, l.clone().add(-.6, .4, .6), 1, 0, 0, 0, 0);
                world.spawnParticle(Particle.FLAME, l.clone().add(-.5, .6, .5), 1, 0, 0, 0, 0);
                world.spawnParticle(Particle.FLAME, l.clone().add(-.4, .8, .4), 1, 0, 0, 0, 0);

                world.spawnParticle(Particle.FLAME, l.clone().add(-1, 0, -1), 1, 0, 0, 0, 0);
                world.spawnParticle(Particle.FLAME, l.clone().add(-.9, .1, -.9), 1, 0, 0, 0, 0);
                world.spawnParticle(Particle.FLAME, l.clone().add(-.8, .2, -.8), 1, 0, 0, 0, 0);
                world.spawnParticle(Particle.FLAME, l.clone().add(-.7, .3, -.7), 1, 0, 0, 0, 0);
                world.spawnParticle(Particle.FLAME, l.clone().add(-.6, .4, -.6), 1, 0, 0, 0, 0);
                world.spawnParticle(Particle.FLAME, l.clone().add(-.5, .6, -.5), 1, 0, 0, 0, 0);
                world.spawnParticle(Particle.FLAME, l.clone().add(-.4, .8, -.4), 1, 0, 0, 0, 0);

                world.spawnParticle(Particle.FLAME, l.clone().add(1, 0, -1), 1, 0, 0, 0, 0);
                world.spawnParticle(Particle.FLAME, l.clone().add(.9, .1, -.9), 1, 0, 0, 0, 0);
                world.spawnParticle(Particle.FLAME, l.clone().add(.8, .2, -.8), 1, 0, 0, 0, 0);
                world.spawnParticle(Particle.FLAME, l.clone().add(.7, .3, -.7), 1, 0, 0, 0, 0);
                world.spawnParticle(Particle.FLAME, l.clone().add(.6, .4, -.6), 1, 0, 0, 0, 0);
                world.spawnParticle(Particle.FLAME, l.clone().add(.5, .6, -.5), 1, 0, 0, 0, 0);
                world.spawnParticle(Particle.FLAME, l.clone().add(.4, .8, -.4), 1, 0, 0, 0, 0);
            }
        }.runAtFixedRate(0, 5));
    }
    
    public void playFireStorm(final Location location, String id) {
        locations.put(id, new FoliaScheduler(this.plugin, location) {
            final Location l = location.add(.5, 2, .5);

            final World world = location.getWorld();

            @Override
            public void run() {
                try {
                    if (!noPlayers(l.clone(), range)) {
                        world.spawnParticle(Particle.LARGE_SMOKE, l, 15, 0.3f, 0, 0.3f, 0);
                        world.spawnParticle(Particle.FLAME, randomDrop(l.clone()), 0, 0, -0.2f, 0, 1);
                        world.spawnParticle(Particle.FLAME, randomDrop(l.clone()), 0, 0, -0.2f, 0, 1);
                    }
                } catch (Exception e) {
                    locations.get(id).cancel();
                    locations.remove(id); // this might need further testing
                    e.printStackTrace();
                }
            }
        }.runAtFixedRate(0, 2));
    }
    
    public void playSnow(final Location location, String id) {
        locations.put(id, new FoliaScheduler(this.plugin, location) {
            final Location l = location.add(.5, 2, .5);

            final World world = location.getWorld();

            @Override
            public void run() {
                if (noPlayers(l.clone(), range)) return;
                world.spawnParticle(Particle.FIREWORK, l, 1, .7f, .7f, .7f, 0);
            }
        }.runAtFixedRate(0, 2));
    }
    
    public void playSpew(final Location location, String id) {
        locations.put(id, new FoliaScheduler(this.plugin, location) {
            final Location l = location.add(.5, 1, .5);

            final World world = location.getWorld();

            @Override
            public void run() {
                if (noPlayers(l.clone(), range)) return;
                world.spawnParticle(Particle.FIREWORK, l, 0, randomVector(), .1f, randomVector(), 1);
            }
        }.runAtFixedRate(0, 2));
    }
    
    public void playPotion(final Location location, String id) {
        locations.put(id, new FoliaScheduler(this.plugin, location) {
            final Location l = location.add(.5, .2, .5);

            final World world = location.getWorld();

            @Override
            public void run() {
                if (noPlayers(l.clone(), range)) return;
                world.spawnParticle(Particle.INSTANT_EFFECT, l, 6, .3f, 0, .3f, randomColor());
                world.spawnParticle(Particle.INSTANT_EFFECT, l, 6, .3f, 0, .3f, randomColor());
                world.spawnParticle(Particle.INSTANT_EFFECT, l, 6, .3f, 0, .3f, randomColor());
            }
        }.runAtFixedRate(0, 2));
    }
    
    public void playMusic(final Location location, String id) {
        locations.put(id, new FoliaScheduler(this.plugin, location) {
            final Location l = location.clone().add(.5, .2, .5);
            final Location[] locs = circle(l, 1, 16);
            int time = 0;

            final World world = location.getWorld();

            @Override
            public void run() {
                if (noPlayers(l, range)) return;
                int i = time;

                switch (time) {
                    case 15 -> {
                        world.spawnParticle(Particle.NOTE, locs[i], 1, 0, 0, 0, randomColor());
                        time = -1;
                    }

                    case 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14 -> world.spawnParticle(Particle.NOTE, locs[i], 1, 0, 0, 0, randomColor());
                }

                time++;
            }
        }.runAtFixedRate(0, 2));
    }
    
    public void playMagic(final Location location, String id) {
        locations.put(id, new FoliaScheduler(this.plugin, location) {
            final Location l = location.add(.5, .5, .5);

            final World world = location.getWorld();
            int time = 16;

            @Override
            public void run() {
                if (noPlayers(l.clone(), range)) return;

                switch (time) {
                    case 0 -> {
                        world.spawnParticle(Particle.CRIT, l.clone().add(0, .75, .43), 5, 0, 0, 0, 0);
                        world.spawnParticle(Particle.CRIT, l.clone().add(.75, 0, -.43), 5, 0, 0, 0, 0);
                        time = 16;
                    }

                    case 1 -> {
                        world.spawnParticle(Particle.CRIT, l.clone().add(0, .65, .65), 5, 0, 0, 0, 0);
                        world.spawnParticle(Particle.CRIT, l.clone().add(.65, 0, -.65), 5, 0, 0, 0, 0);
                    }

                    case 2 -> {
                        world.spawnParticle(Particle.CRIT, l.clone().add(0, .43, .75), 5, 0, 0, 0, 0);
                        world.spawnParticle(Particle.CRIT, l.clone().add(.43, 0, -.75), 5, 0, 0, 0, 0);
                    }

                    case 3 -> {
                        world.spawnParticle(Particle.CRIT, l.clone().add(0, 0, .8), 5, 0, 0, 0, 0);
                        world.spawnParticle(Particle.CRIT, l.clone().add(0, 0, -.8), 5, 0, 0, 0, 0);
                    }

                    case 4 -> {
                        world.spawnParticle(Particle.CRIT, l.clone().add(0, -.43, .75), 5, 0, 0, 0, 0);
                        world.spawnParticle(Particle.CRIT, l.clone().add(-.43, 0, -.75), 5, 0, 0, 0, 0);
                    }

                    case 5 -> {
                        world.spawnParticle(Particle.CRIT, l.clone().add(0, -.65, .65), 5, 0, 0, 0, 0);
                        world.spawnParticle(Particle.CRIT, l.clone().add(-.65, 0, -.65), 5, 0, 0, 0, 0);
                    }

                    case 6 -> world.spawnParticle(Particle.CRIT, l.clone().add(-.75, 0, -.43), 5, 0, 0, 0, 0);

                    case 7 -> {
                        world.spawnParticle(Particle.CRIT, l.clone().add(0, -.8, 0), 5, 0, 0, 0, 0);
                        world.spawnParticle(Particle.CRIT, l.clone().add(-.8, 0, 0), 5, 0, 0, 0, 0);
                    }

                    case 8 -> {
                        world.spawnParticle(Particle.CRIT, l.clone().add(0, -.75, -.43), 5, 0, 0, 0, 0);
                        world.spawnParticle(Particle.CRIT, l.clone().add(-.75, 0, .43), 5, 0, 0, 0, 0);
                    }

                    case 9 -> {
                        world.spawnParticle(Particle.CRIT, l.clone().add(0, -.65, -.65), 5, 0, 0, 0, 0);
                        world.spawnParticle(Particle.CRIT, l.clone().add(-.65, 0, .65), 5, 0, 0, 0, 0);
                    }

                    case 10 -> {
                        world.spawnParticle(Particle.CRIT, l.clone().add(0, -.43, -.75), 5, 0, 0, 0, 0);
                        world.spawnParticle(Particle.CRIT, l.clone().add(-.43, 0, .75), 5, 0, 0, 0, 0);
                    }

                    case 11 -> {
                        world.spawnParticle(Particle.CRIT, l.clone().add(0, 0, -.8), 5, 0, 0, 0, 0);
                        world.spawnParticle(Particle.CRIT, l.clone().add(0, 0, .8), 5, 0, 0, 0, 0);
                    }

                    case 12 -> {
                        world.spawnParticle(Particle.CRIT, l.clone().add(0, .43, -.75), 5, 0, 0, 0, 0);
                        world.spawnParticle(Particle.CRIT, l.clone().add(.43, 0, .75), 5, 0, 0, 0, 0);
                    }

                    case 13 -> {
                        world.spawnParticle(Particle.CRIT, l.clone().add(0, .65, -.65), 5, 0, 0, 0, 0);
                        world.spawnParticle(Particle.CRIT, l.clone().add(.65, 0, .65), 5, 0, 0, 0, 0);
                    }

                    case 14 -> {
                        world.spawnParticle(Particle.CRIT, l.clone().add(0, .75, -.43), 5, 0, 0, 0, 0);
                        world.spawnParticle(Particle.CRIT, l.clone().add(.75, 0, .43), 5, 0, 0, 0, 0);
                    }

                    case 15 -> {
                        world.spawnParticle(Particle.CRIT, l.clone().add(0, .8, 0), 5, 0, 0, 0, 0);
                        world.spawnParticle(Particle.CRIT, l.clone().add(.8, 0, 0), 5, 0, 0, 0, 0);
                    }

                    case 86 -> world.spawnParticle(Particle.CRIT, l.clone().add(0, -.75, .43), 5, 0, 0, 0, 0);
                }

                time--;
            }
        }.runAtFixedRate(0, 2));
    }
    
    public void playSnowStorm(final Location location, String id) {
        locations.put(id, new FoliaScheduler(this.plugin, location) {
            final Location l = location.add(.5, 2, .5);

            final World world = location.getWorld();

            @Override
            public void run() {
                if (noPlayers(l.clone(), range)) return;
                world.spawnParticle(Particle.CLOUD, l, 15, .3f, 0, .3f, 0);
                world.spawnParticle(Particle.FIREWORK, l, 2, .3f, 0, .3f, 0);
            }
        }.runAtFixedRate(0, 2));
    }
    
    public void playFireSpew(final Location location, String id) {
        locations.put(id, new FoliaScheduler(this.plugin, location) {
            final Location l = location.add(.5, 1, .5);

            final World world = location.getWorld();

            @Override
            public void run() {
                if (noPlayers(l.clone(), range)) return;
                world.spawnParticle(Particle.FLAME, l, 0, randomVector(), .1f, randomVector(), 1.5f);
                world.spawnParticle(Particle.FLAME, l, 0, randomVector(), .1f, randomVector(), 1.5f);
                world.spawnParticle(Particle.FLAME, l, 0, randomVector(), .1f, randomVector(), 1.5f);
            }
        }.runAtFixedRate(0, 2));
    }
    
    public void playFootPrint(final Location location, String id) {
        locations.put(id, new FoliaScheduler(this.plugin, location) {
            final Location l = location.add(.5, .1, .5);

            final World world = location.getWorld();

            @Override
            public void run() {
                if (noPlayers(l.clone(), range)) return;
                world.spawnParticle(Particle.EGG_CRACK, l, 3, 1, 0, 1, 0);
            }
        }.runAtFixedRate(0, 20));
    }
    
    public void playHappyVillager(final Location location, String id) {
        locations.put(id, new FoliaScheduler(this.plugin, location) {
            final Location l = location.add(.5, .1, .5);

            final World world = location.getWorld();

            @Override
            public void run() {
                if (noPlayers(l.clone(), range)) return;
                world.spawnParticle(Particle.HAPPY_VILLAGER, l, 10, .5f, .5f, .5f, 0);
            }
        }.runAtFixedRate(0, 5));
    }
    
    public void playAngryVillager(final Location location, String id) {
        locations.put(id, new FoliaScheduler(this.plugin, location) {
            final Location l = location.add(.5, .1, .5);

            final World world = location.getWorld();

            @Override
            public void run() {
                if (noPlayers(l.clone(), range)) return;
                world.spawnParticle(Particle.ANGRY_VILLAGER, l, 5, .5f, .5f, .5f, 0);
            }
        }.runAtFixedRate(0, 10));
    }
    
    public void playMobSpawner(final Location location, String id) {
        locations.put(id, new FoliaScheduler(this.plugin, location) {
            final Location l = location.add(.5, .1, .5);

            final World world = location.getWorld();

            @Override
            public void run() {
                if (noPlayers(l.clone(), range)) return;
                world.spawnParticle(Particle.FLAME, l, 15, .5f, .5f, .5f, 0);
            }
        }.runAtFixedRate(0, 8));
    }
    
    public void startWater(final Location location, String id) {
        locations.put(id, new FoliaScheduler(this.plugin, location) {
            final Location l = location.add(.5, .8, .6);

            final World world = location.getWorld();

            @Override
            public void run() {
                if (noPlayers(l.clone(), range)) return;
                world.spawnParticle(Particle.FALLING_WATER, l.clone().add(0, .1, 0), 10, 0, 0, 0, 0);
                world.spawnParticle(Particle.FALLING_WATER, l.clone().add(0, .5, 0), 10, 0, 0, 0, 0);
                world.spawnParticle(Particle.FALLING_WATER, l.clone().add(.2, .3, .2), 10, 0, 0, 0, 0);
                world.spawnParticle(Particle.FALLING_WATER, l.clone().add(-.2, .3, .2), 10, 0, 0, 0, 0);
                world.spawnParticle(Particle.FALLING_WATER, l.clone().add(.2, .3, -.2), 10, 0, 0, 0, 0);
                world.spawnParticle(Particle.FALLING_WATER, l.clone().add(-.2, .3, -.2), 10, 0, 0, 0, 0);
            }
        }.runAtFixedRate(0, 2));
    }
    
    public void playEnderSignal(final Location location, String id) {
        locations.put(id, new FoliaScheduler(this.plugin, location) {
            final Location l = location.add(.5, 0, .5);

            final World world = location.getWorld();

            @Override
            public void run() {
                if (noPlayers(l.clone(), range)) return;
                world.playEffect(l, Effect.ENDER_SIGNAL, 1);
                world.playEffect(l, Effect.ENDER_SIGNAL, 1);
                world.playEffect(l, Effect.ENDER_SIGNAL, 1);
                world.playEffect(l, Effect.ENDER_SIGNAL, 1);
            }
        }.runAtFixedRate(0, 8));
    }
    
    public void playRainbow(final Location location, String id) {
        locations.put(id, new FoliaScheduler(this.plugin, location) {
            final Location l = location.add(.5, .1, .5);

            final World world = location.getWorld();

            @Override
            public void run() {
                if (noPlayers(l.clone(), range)) return;
                ThreadLocalRandom random = ThreadLocalRandom.current();
                int r = random.nextInt(255);
                int g = random.nextInt(255);
                int b = random.nextInt(255);
                Particle.DustOptions dustOptions = new Particle.DustOptions(Color.fromRGB(r, g, b), 1);
                world.spawnParticle(Particle.DUST, l, 10, .5f, .5f, .5f, 1, dustOptions);
            }
        }.runAtFixedRate(0, 5));
    }
    
    public void playSnowBlast(final Location location, String id) {
        locations.put(id, new FoliaScheduler(this.plugin, location) {
            final Location l = location.add(.5, .5, .5);

            final World world = location.getWorld();

            @Override
            public void run() {
                if (noPlayers(l.clone(), range)) return;
                world.spawnParticle(Particle.SNOWFLAKE, l, 40, 0, 0, 0, .2f);
            }
        }.runAtFixedRate(0, 2));
    }
    
    public void playHalo(final Location location, String id) {
        locations.put(id, new FoliaScheduler(this.plugin, location) {
            final Location l = location.add(.5, 1.3, .5);

            final World world = location.getWorld();

            @Override
            public void run() {
                if (noPlayers(l.clone(), range)) return;
                try {
                    for (int i = 0; i < 3; i++) {
                        world.spawnParticle(Particle.DUST, l.clone().add(.5, 0, 0), 1, 0, 0, 0, 1, new Particle.DustOptions(Color.fromRGB(255, 255, 0), 1));
                        world.spawnParticle(Particle.DUST, l.clone().add(.45, 0, .13), 1, 0, 0, 0, 1, new Particle.DustOptions(Color.fromRGB(255, 255, 0), 1));
                        world.spawnParticle(Particle.DUST, l.clone().add(.35, 0, .35), 1, 0, 0, 0, 1, new Particle.DustOptions(Color.fromRGB(255, 255, 0), 1));
                        world.spawnParticle(Particle.DUST, l.clone().add(.13, 0, .45), 1, 0, 0, 0, 1, new Particle.DustOptions(Color.fromRGB(255, 255, 0), 1));
                        world.spawnParticle(Particle.DUST, l.clone().add(0, 0, .5), 1, 0, 0, 0, 1, new Particle.DustOptions(Color.fromRGB(255, 255, 0), 1));
                        world.spawnParticle(Particle.DUST, l.clone().add(-.13, 0, .45), 1, 0, 0, 0, 1, new Particle.DustOptions(Color.fromRGB(255, 255, 0), 1));
                        world.spawnParticle(Particle.DUST, l.clone().add(-.35, 0, .35), 1, 0, 0, 0, 1, new Particle.DustOptions(Color.fromRGB(255, 255, 0), 1));
                        world.spawnParticle(Particle.DUST, l.clone().add(-.45, 0, .13), 1, 0, 0, 0, 1, new Particle.DustOptions(Color.fromRGB(255, 255, 0), 1));
                        world.spawnParticle(Particle.DUST, l.clone().add(-.5, 0, 0), 1, 0, 0, 0, 1, new Particle.DustOptions(Color.fromRGB(255, 255, 0), 1));
                        world.spawnParticle(Particle.DUST, l.clone().add(-.45, 0, -.13), 1, 0, 0, 0, 1, new Particle.DustOptions(Color.fromRGB(255, 255, 0), 1));
                        world.spawnParticle(Particle.DUST, l.clone().add(-.35, 0, -.35), 1, 0, 0, 0, 1, new Particle.DustOptions(Color.fromRGB(255, 255, 0), 1));
                        world.spawnParticle(Particle.DUST, l.clone().add(-.13, 0, -.45), 1, 0, 0, 0, 1, new Particle.DustOptions(Color.fromRGB(255, 255, 0), 1));
                        world.spawnParticle(Particle.DUST, l.clone().add(0, 0, -.5), 1, 0, 0, 0, 1, new Particle.DustOptions(Color.fromRGB(255, 255, 0), 1));
                        world.spawnParticle(Particle.DUST, l.clone().add(.13, 0, -.45), 1, 0, 0, 0, 1, new Particle.DustOptions(Color.fromRGB(255, 255, 0), 1));
                        world.spawnParticle(Particle.DUST, l.clone().add(.35, 0, -.35), 1, 0, 0, 0, 1, new Particle.DustOptions(Color.fromRGB(255, 255, 0), 1));
                        world.spawnParticle(Particle.DUST, l.clone().add(.45, 0, -.13), 1, 0, 0, 0, 1, new Particle.DustOptions(Color.fromRGB(255, 255, 0), 1));
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }.runAtFixedRate(0, 5));
    }
    
    public void playSantaHat(final Location location, String id) {
        locations.put(id, new FoliaScheduler(this.plugin, location) {
            final Location l1 = location.clone().add(.5, 1, .5);
            final Location l2 = l1.clone().add(0, .05, 0);
            final Location l3 = l2.clone().add(0, .05, 0);
            final Location l4 = l3.clone().add(0, .05, 0);
            final Location l5 = l4.clone().add(0, .05, 0);
            final Location l6 = l5.clone().add(0, .05, 0);
            final Location l7 = l6.clone().add(0, .05, 0);
            final Location l8 = l7.clone().add(0, .05, 0);
            final Location l9 = l8.clone().add(0, .05, 0);
            final Location l10 = l9.clone().add(0, .1, 0);
            final Location l11 = l10.clone().add(0, .05, 0);

            final World world = location.getWorld();
            final Particle.DustOptions red = new Particle.DustOptions(Color.RED, 1);
            final Particle.DustOptions white = new Particle.DustOptions(Color.fromRGB(255, 255, 255), 1);

            final Location[] c1 = circle(l1, .5, 20);
            final Location[] c2 = circle(l2, .4, 15);
            final Location[] c3 = circle(l3, .35, 15);
            final Location[] c4 = circle(l4, .3, 15);
            final Location[] c5 = circle(l5, .2, 15);
            final Location[] c6 = circle(l6, .15, 15);
            final Location[] c7 = circle(l7, .1, 15);
            final Location[] c8 = circle(l8, .05, 10);
            final Location[] c9 = circle(l9, .05, 10);
            final Location[] c10 = circle(l10, .05, 15);
            final Location[] c11 = circle(l11, .05, 15);

            @Override
            public void run() {
                if (noPlayers(l1, 20)) return;
                try {
                    for (int i = 0; i < 3; i++) {
                        for (Location location : c1)
                            world.spawnParticle(Particle.DUST, location, 1, 0, 0, 0, 0, white);
                        for (Location location : c2)
                            world.spawnParticle(Particle.DUST, location, 1, 0, 0, 0, 0, red);
                        for (Location location : c3)
                            world.spawnParticle(Particle.DUST, location, 1, 0, 0, 0, 0, red);
                        for (Location location : c4)
                            world.spawnParticle(Particle.DUST, location, 1, 0, 0, 0, 0, red);
                        for (Location location : c5)
                            world.spawnParticle(Particle.DUST, location, 1, 0, 0, 0, 0, red);
                        for (Location location : c6)
                            world.spawnParticle(Particle.DUST, location, 1, 0, 0, 0, 0, red);
                        for (Location location : c7)
                            world.spawnParticle(Particle.DUST, location, 1, 0, 0, 0, 0, red);
                        for (Location location : c8)
                            world.spawnParticle(Particle.DUST, location, 1, 0, 0, 0, 0, red);
                        for (Location location : c9)
                            world.spawnParticle(Particle.DUST, location, 1, 0, 0, 0, 0, red);
                        for (Location location : c10)
                            world.spawnParticle(Particle.DUST, location, 1, 0, 0, 0, 0, white);
                        for (Location location : c11)
                            world.spawnParticle(Particle.DUST, location, 1, 0, 0, 0, 0, white);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }.runAtFixedRate(0, 5));
    }
    
    public void playSoulWell(final Location location, final String id) {
        final HashMap<Integer, ScheduledTask> S = new HashMap<>();

        locations.put(id, new FoliaScheduler(this.plugin, location) {
            final Location l = location.clone().add(.5, 0, .5);

            void startSoulWell(final Location location, final String id) {
                final int num = ThreadLocalRandom.current().nextInt(Integer.MAX_VALUE);

                S.put(num, new FoliaScheduler(plugin, location) {
                    final Location height = location.clone();
                    final World world = height.getWorld();
                    int loc = 0;
                    int lifeSpan = 0;

                    @Override
                    public void run() {
                        double angle = (2 * Math.PI * loc) / 50;
                        double dx = 2 * Math.cos(angle);
                        double dz = 2 * Math.sin(angle);
                        world.spawnParticle(Particle.WITCH, height.clone().add(dx, 0, dz), 1, 0, 0, 0, 0);
                        world.spawnParticle(Particle.WITCH, height.clone().add(-dx, 0, -dz), 1, 0, 0, 0, 0);
                        loc++;
                        lifeSpan++;
                        height.add(0, .035, 0);
                        if (loc == 50) loc = 0;
                        if (lifeSpan == 75) {
                            S.get(num).cancel();
                            S.remove(num);
                        }
                    }
                }.runAtFixedRate(0, 1));
            }

            @Override
            public void run() {
                if (noPlayers(l.clone(), range)) return;
                startSoulWell(l, id);
            }
        }.runAtFixedRate(0, 16));
    }
    
    public void playBigSoulWell(final Location location, final String id) {
        final HashMap<Integer, ScheduledTask> S = new HashMap<>();

        locations.put(id, new FoliaScheduler(this.plugin, location) {
            final Location l = location.clone().add(.5, 0, .5);

            void startBigSoulWell(final Location location, final String id) {
                final int num = ThreadLocalRandom.current().nextInt(Integer.MAX_VALUE);
                S.put(num, new FoliaScheduler(plugin, location) {
                    final Location height = location.clone();
                    final World world = height.getWorld();
                    int loc = 0;
                    int lifeSpan = 0;

                    @Override
                    public void run() {
                        double angle = (2 * Math.PI * loc) / 75;
                        double dx = 3.5 * Math.cos(angle);
                        double dz = 3.5 * Math.sin(angle);
                        world.spawnParticle(Particle.WITCH, height.clone().add(dx, 0, dz), 1, 0, 0, 0, 0);
                        world.spawnParticle(Particle.WITCH, height.clone().add(-dx, 0, -dz), 1, 0, 0, 0, 0);
                        loc++;
                        lifeSpan++;
                        height.add(0, .04, 0);
                        if (loc == 75) loc = 0;
                        if (lifeSpan == 105) {
                            S.get(num).cancel();
                            S.remove(num);
                        }
                    }
                }.runAtFixedRate(0, 1));
            }

            @Override
            public void run() {
                if (noPlayers(l.clone(), range)) return;
                startBigSoulWell(l, id);
            }
        }.runAtFixedRate(0, 25));
    }
    
    public void playFlameWheel(final Location location, final String id) {
        final HashMap<Integer, ScheduledTask> S = new HashMap<>();

        locations.put(id, new FoliaScheduler(this.plugin, location) {
            final Location l = location.clone().add(.5, .1, .5);

            void startFlameWheel(final Location location) {
                final int num = ThreadLocalRandom.current().nextInt(Integer.MAX_VALUE);
                S.put(num, new FoliaScheduler(plugin, location) {
                    final Location center = location.clone();
                    final World world = location.getWorld();
                    final Location[] ring = new Location[75];
                    final double[] dx = new double[75];
                    final double[] dz = new double[75];
                    int i = 0;
                    int o = 74;
                    int f = 0;
                    int ringTimer = 0;

                    {
                        double increment = (2 * Math.PI) / 75;
                        for (int n = 0; n < 75; n++) {
                            double angle = n * increment;
                            dx[n] = 3.5 * Math.cos(angle);
                            dz[n] = 3.5 * Math.sin(angle);
                            ring[n] = center.clone().add(dx[n], 0, dz[n]);
                        }
                    }

                    @Override
                    public void run() {
                        float speed = (float) .15;
                        double invRadius = 1.0 / 3.5;
                        double xi = dx[i];
                        double zi = dz[i];
                        double xo = dx[o];
                        double zo = dz[o];
                        float vxi = (float) (xi * invRadius);
                        float vzi = (float) (zi * invRadius);
                        float vxo = (float) (xo * invRadius);
                        float vzo = (float) (zo * invRadius);
                        //Makes the ring around the edge
                        if (ringTimer == 10) {
                            for (Location location : ring) {
                                world.spawnParticle(Particle.FLAME, location, 0);
                            }
                        }

                        //Throws the fire inwords.
                        world.spawnParticle(Particle.FLAME, center.clone().add(xi, 0, zi), 0, -vxi, 0, -vzi, speed);
                        world.spawnParticle(Particle.FLAME, center.clone().add(-xi, 0, -zi), 0, vxi, 0, vzi, speed);
                        world.spawnParticle(Particle.FLAME, center.clone().add(xo, 0, zo), 0, -vxo, 0, -vzo, speed);
                        world.spawnParticle(Particle.FLAME, center.clone().add(-xo, 0, -zo), 0, vxo, 0, vzo, speed);
                        i++;
                        f++;
                        o--;
                        ringTimer++;
                        if (ringTimer == 11) ringTimer = 0;
                        if (i == 75) i = 0;
                        if (o == 0) o = 74;
                        if (f == 105) {
                            S.get(num).cancel();
                            S.remove(num);
                        }
                    }
                }.runAtFixedRate(0, 1));
            }

            @Override
            public void run() {
                if (noPlayers(l.clone(), range)) return;
                startFlameWheel(l.clone());
            }
        }.runAtFixedRate(0, 25));
    }
    
    public void playWitchTornado(final Location location, final String id) {
        final HashMap<Integer, ScheduledTask> S = new HashMap<>();

        locations.put(id, new FoliaScheduler(this.plugin, location) {
            final Location l = location.clone().add(.5, .1, .5);

            void startWitchTornado(final Location location) {
                final int num = ThreadLocalRandom.current().nextInt(Integer.MAX_VALUE);
                S.put(num, new FoliaScheduler(plugin, location) {
                    final Location height = location.clone().add(0, 5, 0);
                    final World world = height.getWorld();
                    int nextLocation = 0;
                    int diameterSwitch = 0;
                    double radius = 1.5;
                    int lifeSpan = 0;

                    @Override
                    public void run() {
                        double angle = (2 * Math.PI * nextLocation) / 50;
                        world.spawnParticle(Particle.WITCH, height.clone().add(radius * Math.cos(angle), 0, radius * Math.sin(angle)), 0, 0, 0, 0, 1);
                        nextLocation++;
                        diameterSwitch++;
                        lifeSpan++;
                        if (nextLocation == 50) nextLocation = 0;
                        height.add(0, -.02, 0); //Controls how far each particle goes Down.
                        if (diameterSwitch == 7) { //Controls when diameter Changes.
                            diameterSwitch = 0;
                            radius = radius - .05; //Controls how far it goes in.
                        }
                        if (lifeSpan == 207) { //Controls how far the particle effect go down.
                            S.get(num).cancel();
                            S.remove(num);
                        }
                    }
                }.runAtFixedRate(0, 1));
            }

            @Override
            public void run() {
                if (noPlayers(l.clone(), range)) return;
                startWitchTornado(l);
            }
        }.runAtFixedRate(0, 30));
    }
    
    public void playLoveTornado(final Location location, final String id) {
        final HashMap<Integer, ScheduledTask> S = new HashMap<>();

        locations.put(id, new FoliaScheduler(this.plugin, location) {
            final Location l = location.clone().add(.5, 0, .5);

            void startLoveTornado(final Location location) {
                final int num = ThreadLocalRandom.current().nextInt(Integer.MAX_VALUE);
                S.put(num, new FoliaScheduler(plugin, location) {

                    final Location height = location.clone().add(0, 5, 0);
                    final World world = height.getWorld();
                    int diameterShrink = 0;
                    double radius = 1.5;
                    int lifeSpan = 0;
                    int nextLocation = 0;

                    @Override
                    public void run() {
                        double angle = (2 * Math.PI * nextLocation) / 50;
                        world.spawnParticle(Particle.HEART, height.clone().add(radius * Math.cos(angle), 0, radius * Math.sin(angle)), 0, 0, 0, 0, 1);
                        diameterShrink++;
                        lifeSpan++;
                        nextLocation++;
                        if (nextLocation == 50) nextLocation = 0; //Controls the next x & z locations.
                        height.add(0, -.02, 0); //Controls how far each particle goes Down.
                        if (diameterShrink == 7) { //Controls when diameter Changes.
                            diameterShrink = 0;
                            radius = radius - .05; //Controls how far it goes in.
                        }
                        if (lifeSpan == 207) { //Controls how far the particle effect go down.
                            S.get(num).cancel();
                            S.remove(num);
                        }
                    }
                }.runAtFixedRate(0, 1));
            }

            @Override
            public void run() {
                if (noPlayers(l.clone(), range)) return;
                startLoveTornado(l);
            }
        }.runAtFixedRate(0, 30));
    }
    
    public void playBigLoveWell(final Location location, final String id) {
        final HashMap<Integer, ScheduledTask> S = new HashMap<>();

        locations.put(id, new FoliaScheduler(this.plugin, location) {
            final Location l = location.clone().add(.5, 0, .5);

            void startBigLoveWell(final Location location) {
                final int num = ThreadLocalRandom.current().nextInt(Integer.MAX_VALUE);
                S.put(num, new FoliaScheduler(plugin, location) {
                    final Location height = location.clone();
                    final World world = height.getWorld();
                    int loc = 0;
                    int lifeSpan = 0;

                    @Override
                    public void run() {
                        double angle = (2 * Math.PI * loc) / 75;
                        double dx = 3.5 * Math.cos(angle);
                        double dz = 3.5 * Math.sin(angle);
                        world.spawnParticle(Particle.HEART, height.clone().add(dx, 0, dz), 1, 0, 0, 0, 0);
                        world.spawnParticle(Particle.HEART, height.clone().add(-dx, 0, -dz), 1, 0, 0, 0, 0);
                        loc++;
                        lifeSpan++;
                        height.add(0, .04, 0);
                        if (loc == 75) loc = 0;
                        if (lifeSpan == 105) {
                            S.get(num).cancel();
                            S.remove(num);
                        }
                    }
                }.runAtFixedRate(0, 1));
            }

            @Override
            public void run() {
                if (noPlayers(l.clone(), range)) return;
                startBigLoveWell(l);
            }
        }.runAtFixedRate(0, 25));
    }
    
    public void playLoveWell(final Location location, final String id) {
        final HashMap<Integer, ScheduledTask> S = new HashMap<>();

        locations.put(id, new FoliaScheduler(this.plugin, location) {
            Location l = location.clone().add(.5, 0, .5);

            void startLoveWell(final Location location) {
                final int num = ThreadLocalRandom.current().nextInt(Integer.MAX_VALUE);
                S.put(num, new FoliaScheduler(plugin, location) {
                    final Location height = location.clone();
                    final World world = height.getWorld();
                    int loc = 0;
                    int lifeSpan = 0;

                    @Override
                    public void run() {
                        double angle = (2 * Math.PI * loc) / 50;
                        double dx = 2 * Math.cos(angle);
                        double dz = 2 * Math.sin(angle);
                        world.spawnParticle(Particle.HEART, height.clone().add(dx, 0, dz), 1, 0, 0, 0, 0);
                        world.spawnParticle(Particle.HEART, height.clone().add(-dx, 0, -dz), 1, 0, 0, 0, 0);
                        loc++;
                        lifeSpan++;
                        height.add(0, .035, 0);
                        if (loc == 50) loc = 0;
                        if (lifeSpan == 75) {
                            S.get(num).cancel();
                            S.remove(num);
                        }
                    }
                }.runAtFixedRate(0, 1));
            }

            @Override
            public void run() {
                if (noPlayers(l.clone(), range)) return;
                startLoveWell(l);
            }
        }.runAtFixedRate(0, 16));
    }
    
    private static Location[] circle(Location center, double radius, int amount) {
        World world = center.getWorld();
        double increment = (2 * Math.PI) / amount;
        double cx = center.getX();
        double cy = center.getY();
        double cz = center.getZ();
        Location[] locations = new Location[amount];

        for (int i = 0; i < amount; i++) {
            double angle = i * increment;
            locations[i] = new Location(world, cx + radius * Math.cos(angle), cy, cz + radius * Math.sin(angle));
        }

        return locations;
    }
    
    private boolean noPlayers(Location location, double range) {
        return location.getWorld().getNearbyPlayers(location, range).isEmpty();
    }
    
    private int randomColor() {
        return ThreadLocalRandom.current().nextInt(255);
    }
    
}