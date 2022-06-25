package me.sashak.inventoryutil.filter.slot;

import org.bukkit.inventory.Inventory;

import java.util.Arrays;

class RangedSlotGroup extends SlotGroup {
	
	private final int min;
	private final int max;
	private final int[] array;
	
	public RangedSlotGroup(int minInclusive, int maxInclusive) {
		if (minInclusive < 0) throw new IllegalArgumentException("min slot must be >= 0. min slot is " + minInclusive);
		if (minInclusive > maxInclusive) throw new IllegalArgumentException("min must be <= max. min slot is " + minInclusive + ", and max slot is " + maxInclusive);
		
		this.min = minInclusive;
		this.max = maxInclusive;
		
		array = new int[max - min + 1];
		for (int i = 0; i < array.length; i++) {
			array[i] = i + min;
		}
	}
	
	@Override
	public boolean contains(Inventory inventory, int slot) {
		return slot >= min && slot <= max && slot < inventory.getSize();
	}
	
	@Override
	public int[] getSlots(Inventory inventory) {
		if (max >= inventory.getSize()) {
			return Arrays.copyOf(array, inventory.getSize() - min);
		} else {
			return Arrays.copyOf(array, array.length);
		}
	}
}
