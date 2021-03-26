package net.minecraft.src;

import java.util.Random;

public class BTABiomeGenValley extends BTABiomeGenWoods {
	public BTABiomeGenValley(int par1) {
		super(par1);
	}

    public void decorate(World var1, Random var2, int var3, int var4)
    {
        super.decorate(var1, var2, var3, var4);
        this.AddEmeralds(var1, var2, var3, var4);
        this.AddSilverfishBlocks(var1, var2, var3, var4);
    }
}