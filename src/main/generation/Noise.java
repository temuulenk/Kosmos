package main.generation;

import org.newdawn.slick.geom.Vector2f;

import java.util.Random;

public class Noise {

    public static float[][] GenerateNoiseMap(int mapWidth, int mapHeight, int seed, float scale, int octaves, float persistence, int lacunarity) {
        float[][] noiseMap = new float[mapHeight][mapWidth];

        Random prng = new Random();
        if(seed == 0)
            prng.setSeed((int) (Math.random() * 9999));
        else if(seed != -1)
            prng.setSeed(seed);


        Vector2f[] octaveOffsets = new Vector2f[octaves];
        for(int i=0; i<octaves; i++) {
//            float offsetX = prng.nextInt((100000 - (-100000)) + 1) + (-100000) + offset.x;
//            float offsetY = prng.nextInt((100000 - (-100000)) + 1) + (-100000) + offset.y;

            float offsetX = prng.nextInt(1000);
            float offsetY = prng.nextInt(1000);

            octaveOffsets[i] = new Vector2f(offsetX, offsetY);
//            octaveOffsets[i] = new Vector2f(20, 20);
        }

        if(scale <= 1)
            scale = 1;

        new Perlin();


        float maxNoiseHeight = Float.MIN_VALUE;
        float minNoiseHeight = Float.MAX_VALUE;

        float halfSizeX = mapWidth / 2;
        float halfSizeY = mapHeight / 2;

        for(int row=0; row<mapHeight; row++) {
            for(int col=0; col<mapWidth; col++) {

                float amplitude = 1;
                float frequency = 1;
                float noiseHeight = 0;

                for(int i=0; i<octaves; i++) {
                    float sampleX = (col - halfSizeX) / scale * frequency + octaveOffsets[i].x;
                    float sampleY = (row - halfSizeY) / scale * frequency + octaveOffsets[i].y;

                    float perlinValue = (float) Perlin.perlin(sampleX, sampleY, 0) * 2 - 1;
                    noiseHeight += perlinValue * amplitude;

                    amplitude *= persistence;
                    frequency *= lacunarity;
                }

                if(noiseHeight > maxNoiseHeight)
                    maxNoiseHeight = noiseHeight;
                else if(noiseHeight < minNoiseHeight)
                    minNoiseHeight = noiseHeight;

                noiseMap[row][col] = noiseHeight;

            }
        }


        // NORMALIZATION
        for(int row=0; row<mapHeight; row++) {
            for(int col=0; col<mapWidth; col++) {
                noiseMap[row][col] = inverseLerp(minNoiseHeight, maxNoiseHeight, noiseMap[row][col]);
            }
        }

        return noiseMap;

    }

    private static float clamp(float value) {
        if (value < 0F)
            return 0F;
        else if (value > 1F)
            return 1F;
        else
            return value;
    }

    private static float inverseLerp(float a, float b, float value) {
        if (a != b)
            return clamp((value - a) / (b - a));
        else
            return 0.0f;
    }

    private static float distance(float x1, float y1, float x2, float y2) {
        return (float) Math.sqrt((Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2)));
    }


}
