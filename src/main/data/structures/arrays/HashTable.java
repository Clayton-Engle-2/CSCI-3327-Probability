package main.data.structures.arrays;

import java.nio.ByteBuffer;
import java.util.Random;

public class HashTable {
	private int HASH_SEED;
	
	private final int size;
	private double[][] keys;
	
	/**
	 * The constructor sets the length of the 2D array to the first prime number larger than the 
	 * input size. Since the results of the hash functions are divided ,modularly to fit the bounds 
	 * of the array, having the size be a prime number will provide a more even distribution of
	 * index values after the % size.
	 * 
	 * The hash seed is randomly generated, its purpose is mostly to ensure each instance of certain 
	 * hashing functions will map differently
	 * 
	 * @param size of array
	 */
	  
	public HashTable(int size) {
	    this.size = getNextPrime(size);
	    keys = new double[this.size][2];
	    
	    Random seed = new Random();
	    HASH_SEED = seed.nextInt();
	}

	private int getNextPrime(int n) {
	    if (n <= 1) {
	        return 2;
	    }
	    int prime = n + 1;
	    while (!isPrime(prime)) {
	        prime++;
	    }
	    return prime;
	}

	private boolean isPrime(int n) {
		if (n <= 1) {
			return false;
		}
		for (int i = 2; i <= Math.sqrt(n); i++) {
			if (n % i == 0) {
				return false;
			}
		}
		return true;
	}
	
	/**
	 * Adds a new key-value pair to the hash table. If the key already exists in the table,
	 * increments the value associated with the key. In this implementation, the key and value
	 * are the same number. 
	 * 
	 * I used double hashing to map values to indexes in the array because double hashing maintains
	 * decent performance even when there are a lot of unique elements in the input and the hash tale 
	 * array is heavily populated, while maintaining the simplicity of a single level table. There are 
	 * faster algorithms that bucket brigade inputs through two or more hash tables to make collisions 
	 * almost impossible, but for the added complexity of the implementation and  extra memory needed
	 * I think double hashing is a fine approach.
	 * 
	 * Basically, if a collision occurs, the index is increased by the number generated from the key being
	 * put through the second hash function. This continues until the input value either finds an empty spot
	 * or finds an equal value already in the table.
	 * 
	 * Index 0 of the 2D array stores the numerical value of input
	 * Index 1 of the 2D array stores the frequency of the element
	 * This method returns the frequency of the element in the table, as it can be helpful to check the frequency
	 * of an element after inserting it without having to call seperate methods and re-hash the key.
	 * 
	 * @param key the key to be added to the hash table
	 * @param value the value associated with the key
	 * @return the updated count of the value associated with the key, or -1 if the value already exists in the table
	*/
	public double add(double key, double value) {
	    int hash1 = hash2(key);
	    int hash2 = hash3(key);
	    int index = Math.abs(hash1 % size);
	    int step = Math.abs(hash2 % (size - 2)) + 1; // ensure step size is relatively prime to table size
	    int i = 0;

	    while (Double.compare(keys[index][1], 0.0d) != 0 && Double.compare(keys[index][0], value) != 0 && i < size) {
	        index = (index + step) % size;
	        i++;
	    }
	    
	    if (Double.compare(keys[index][1], 0.0d) == 0 ) {
	        keys[index][0] = value;
	        keys[index][1] = 1;
	        return keys[index][1];
	    }
	    if (Double.compare(keys[index][0], value) == 0) {
	    	keys[index][1] += 1;
	    	return keys[index][1];
	    } else {
	    	return -1; // value already exists in the table
	    }
	}
		  
	public double getFrequency(double key, double value) {
		int hash1 = hash2(key);
	    int hash2 = hash3(key);
	    int index = Math.abs(hash1 % size);
	    int step = Math.abs(hash2 % (size - 2)) + 1; // ensure step size is relatively prime to table size
	    int i = 0;
	    
	    if (Double.compare(keys[index][1], 0.0d) == 0)  
	    	return 0;
	    	
	    if(Double.compare(keys[index][0], value) == 0) {
	    	return keys[index][1];
	    }

	    while (Double.compare(keys[index][0], 0.0d) != 0 && Double.compare(keys[index][1], value) != 0 && i < size) {
	        index = (index + step) % size;
	        i++;
	    }
	    
	    if (Double.compare(keys[index][1], 0.0d) == 0)  
	    	return 0;
	    	
	    if(Double.compare(keys[index][0], value) == 0) {
	    	return keys[index][1];
	    }
	    
	    return 0;
		
	}
	public boolean contains(double key, double value) {
		boolean exists = false;
		int hash1 = hash2(key);
	    int hash2 = hash3(key);
	    int index = Math.abs(hash1 % size);
	    int step = Math.abs(hash2 % (size - 2)) + 1; // ensure step size is relatively prime to table size
	    int i = 0;
	    
	    if (Double.compare(keys[index][1], 0.0d) == 0)  
	    	return exists;
	    	
	    if(Double.compare(keys[index][0], value) == 0) {
	    	exists = true;
	    	return exists;
	    }

	    while (Double.compare(keys[index][0], 0.0d) != 0 && Double.compare(keys[index][1], value) != 0 && i < size) {
	        index = (index + step) % size;
	        i++;
	    }
	    
	    if (Double.compare(keys[index][1], 0.0d) == 0)  
	    	return exists;
	    	
	    if(Double.compare(keys[index][0], value) == 0) {
	    	exists = true;
	    	return exists;
	    }
	    
	    return exists;
	}
	
	/**
	 * Here are methods which adapt the key input double to either a long or a byte array for hashing
	 * 
	 */	  
	private int hash2(double key) {
		return hashFarm(doubleToLong(key));
	}
	@SuppressWarnings("unused")
	private int hash(double key) {
		return hashMurmur(doubleToByteArray(key), HASH_SEED);
	}
	@SuppressWarnings("unused")
	private int hash(long key) {
		return hashMurmur(longToByteArray(key), HASH_SEED);
	}
	
	private byte[] doubleToByteArray(double value) {
		return longToByteArray(doubleToLong(value));
	}
	
	
	private byte[] longToByteArray(long longBits) {
		byte[] result = new byte[8];
		result[0] = (byte)(longBits >>> 56);
		result[1] = (byte)(longBits >>> 48);
	    result[2] = (byte)(longBits >>> 40);
	    result[3] = (byte)(longBits >>> 32);
	    result[4] = (byte)(longBits >>> 24);
	    result[5] = (byte)(longBits >>> 16);
	    result[6] = (byte)(longBits >>> 8);
	    result[7] = (byte)(longBits);
	    return result;
	}

	
	private long doubleToLong(double value) {
		long resultBits = 0;
		long bits = (long) value;
		if (Double.isNaN(value)) {
			resultBits = 0x7ff8000000000000L;
		} else {
			resultBits = bits;
		}
		if ((bits & (1L << 63)) == 0) {
			resultBits = bits;
		} else {
			resultBits = bits ^ 0x7ff0000000000000L;
		}
		return resultBits;
	}
	    
	/**
	 * Implements the Murmur3 hash algorithm for a given input data and seed.
	 * 
	 * @param data the data to be hashed
	 * @param seed the seed value
	 * @return the integer representation of the hash value
	 */
	private int hashMurmur(byte[] data, int seed) {
/*
 * Variables 
 * ----------------------------------------------
 * C1 and C2: These are constant values used in the algorithm. They are multiplied 
 * 			  with the processed data block during each iteration to change its value.
 * 
 * R1 and R2: These represent the number of rotations that are performed on the data 
 * 		      block during each iteration. A rotation is a bitwise operation where the 
 * 		      bits of a binary number are shifted to the left or right.
 * 
 * M and N:   These are constant values used in the algorithm. They are added to the 
 *            result of each iteration to change its value.
 *            
 * length:    This is the length of the input data, in bytes.
 * 
 *   h:       This is the seed value that is used to initialize the hash value. It is 
 *   		  important because the same seed value will produce the same hash value 
 *            for the same input data.
 *            
 * numBlocks: This is the number of 8-byte blocks that are processed in each iteration 
 * 			  of the main loop. This value is calculated by shifting the length of the 
 * 			  input data to the right by 3 bits (dividing it by 8). This is because 
 * 			  each iteration of the main loop processes an 8-byte block of data.	
 */
		long C1 = 0x87c37b91114253d5L;
		long C2 = 0x4cf5ad432745937fL;
		int R1 = 31;
		int R2 = 27;
		long M = 0xff51afd7ed558ccdL;
		long N = 0xc4ceb9fe1a85ec53L;
		    
		int length = data.length;
		long h = seed & 0x00000000ffffffffL;
		int numBlocks = length >> 3;
/*
 * The first part of the Murmur3 algorithm is used to calculate the hash value for a 
 * 8-byte array data based on the Murmur3 hash function.
 * 
 * The for loop iterates numBlocks times, where numBlocks is calculated by dividing 
 * the length of the data array by 8. At each iteration of the loop, the i8 variable 
 * is calculated by shifting the value of i to the left by 3 bits using the bitwise 
 * operator <<. My understanding is that this is equivalent to multiplying i by 8, 
 * and is used to access the next 8 bytes of the data array.


 */
		        for (int i = 0; i < numBlocks; i++) {
		            int i8 = i << 3;
		            long k = ((long)data[i8] & 0xff)
		                  | (((long)data[i8 + 1] & 0xff) << 8)
		                  | (((long)data[i8 + 2] & 0xff) << 16)
		                  | (((long)data[i8 + 3] & 0xff) << 24)
		                  | (((long)data[i8 + 4] & 0xff) << 32)
		                  | (((long)data[i8 + 5] & 0xff) << 40)
		                  | (((long)data[i8 + 6] & 0xff) << 48)
		                  | (((long)data[i8 + 7] & 0xff) << 56);
/*
 * The k variable is used to store a single 8-byte block of the data array. It is calculated 
 * by combining 8 individual bytes from the data array into a single long value. Each byte is 
 * read from the data array using the data[i8 + n] syntax, where n ranges from 0 to 7. 
 * 
 * The & 0xff operation is used to mask out any bits above the low 8 bits of each byte, 
 * effectively converting each byte to an 8-bit signed integer. The | operator is used 
 * to combine the 8 integers into a single long value.
 * 
 * The k value is then processed as follows:
 * 	-  The k value is multiplied by the constant C1.
 *  -  The k value is rotated left by R1 bits using the Long.rotateLeft method.
 *  -  The k value is multiplied by the constant C2.
 *  -  The k value is XORed with the current h value.
 *  -  The h value is rotated left by R2 bits using the Long.rotateLeft method.
 *  -  The h value is multiplied by M and then incremented by N.
 *  
 *  After processing all of the blocks in the data array, the h value will contain the hash value of the data array.
 */
		            k *= C1;
		            k = (k << R1) | (k >>> -R1);
		            k *= C2;
		            h ^= k;
		            h = (h << R2) | (h >>> -R2);
		            h = h * M + N;
		        }
/*
 * Now the next part of the algorithm is for handling the "tail" of the input data that hasn't been processed in 
 * full 8-byte blocks. The tail variable is calculated by using a bitwise AND operator & with the value 7, which 
 * is the number of bytes in an 8-byte block.
 * 
 * The switch statement is then used to handle the different cases of the number of remaining bytes. 
 * 
 * Once the tail bytes have been processed, the constant C1 is multiplied with k1, and the result is rotated left by 
 * R1 bits using the Long. rotateLeft() method. The result is then multiplied by the constant C2. Finally, h is 
 * updated by performing a bitwise XOR with k1.
 * 
 * The purpose of this code is to make sure that the hash value generated by the algorithm is 
 * consistent, even if the input data has an odd number of bytes.		        
 */
		        int tail = length & 7;
		        long k1 = 0;
		        
		        switch (tail) {
		            case 7:
		                k1 ^= (long)data[length - 7] << 48;
		            case 6:
		                k1 ^= (long)data[length - 6] << 40;
		            case 5:
		                k1 ^= (long)data[length - 5] << 32;
		            case 4:
		                k1 ^= (long)data[length - 4] << 24;
		            case 3:
		                k1 ^= (long)data[length - 3] << 16;
		            case 2:
		                k1 ^= (long)data[length - 2] << 8;
		            case 1:
		                k1 ^= (long)data[length - 1];
		                k1 *= C1;
		                k1 = (k1 << R1) | (k1 >>> -R1);
		                k1 *= C2;
		                h ^= k1;
		        }	        
/*
 * At the final step of the Murmur3 hash function takes the intermediate hash value h computed in previous 
 * steps and mixes it further to produce the final 32-bit hash value using these steps.
 *   -  h ^= length: the algorithm XORs the length of the input data with the intermediate hash value h.
 *   -  h ^= h >>> 33: the algorithm performs a bitwise XOR operation between h and h shifted 33 bits to the right. 
 *      This step further mixes the bits of the intermediate hash value.
 *   -  h *= 0xff51afd7ed558ccdL: the algorithm multiplies the intermediate hash value h by a constant value.
 *   -  h ^= h >>> 33: similar to step 2, the algorithm performs a bitwise XOR operation between h and h shifted 33 
 *      bits to the right.
 *   -  h *= 0xc4ceb9fe1a85ec53L: the algorithm multiplies the intermediate hash value h by another constant value.
 *   -  h ^= h >>> 33: similar to step 2 and 4, the algorithm performs a bitwise XOR operation between h and h 
 *      shifted 33 bits to the right.
 *   
 *   Finally, return (int)(h & 0x00000000ffffffffL): the algorithm returns the lower 32 bits of the intermediate hash 
 *   value h as the final hash value, by using a bitwise AND operation with a constant value. The returned value 
 *   is cast to an int type.	        
 */
		        h ^= length;
		        h ^= h >>> 33;
		        h *= 0xff51afd7ed558ccdL;
		        h ^= h >>> 33;
		        h *= 0xc4ceb9fe1a85ec53L;
		        h ^= h >>> 33;
		        
		        return (int)(h & 0x00000000ffffffffL);
	}
	/**
	 * After researching hashing functions for a bit I realized hashing is a black hole topic 
	 * 
	 * For the sake of time and sanity I generalized what makes a good hash function:
	 * 
	 * Deterministic:         The same input value should always produce the same output value, no matter how many times 
	 * 						  the function is called.
	 * Uniform distribution:  A good hash function should distribute the output values uniformly across the entire range 
	 * 						  of possible output values. This helps to minimize collisions, which occur when two different 
	 * 						  inputs produce the same output.
	 * Efficient:             The hash function should be computationally efficient and require a relatively small amount of 
	 * 						  processing time to generate the output value.
	 * Collision-resistant:   A good hash function should make it difficult to find two different inputs that produce the 
	 * 						  same output value. This is important for security purposes, as it makes it harder for an 
	 * 						  attacker to manipulate the hash function to obtain unauthorized access.
	 * 
	 * With these characteristics in mind I searched for other proven hashing functions from reputable sources. I wrote
	 * adaptor methods for the function input, and did some "plug and play" testing.
	 */
	
	/**
	 * This is a simple implementation of the open-source Farm Hash function created by Google. This function accepts long
	 * values as a parameter, which for me means less time spent converting doubles to byte arrays. This function has no loops
	 * and a relatively low number of operations. For my purposes, this is perfect. 
	 * @param data
	 * @return
	 */
	public int hashFarm(long data) {
		  
		//long k0 = 0xc3a5c85c97cb3127L;
		long k1 = 0xb492b66fbe98f273L;
		long k2 = 0x9ae16a3b2f90404fL;
		long h1 = data * k1;
		h1 ^= h1 >>> 47;
		                
		long h2 = data * k2;
		h2 ^= h2 >>> 47;

		h1 += h2;
		h2 += h1;

		h1 = Long.rotateLeft(h1, 13);
		h2 = Long.rotateLeft(h2, 13);

		h1 *= k1;
		h2 *= k2;

		h1 ^= h1 >>> 47;
		h2 ^= h2 >>> 47;

		h1 += h2;
		h2 += h1;

		return (int) h1;
	}
	
	public int hash3(double key) {
		return hashX(doubleToByteArray(key), HASH_SEED);
	}
	
	public byte[] bufferByte(double value) {
		byte[] bytes = new byte[8];
		ByteBuffer.wrap(bytes).putDouble(value);
		return bytes;
	}
	
	public int hashX(byte[] data, int seed) {
		int PRIME1 = 0x9E3779B1;
		int PRIME2 = 0x85EBCA77;
		int PRIME3 = 0xC2B2AE3D;
		int PRIME4 = 0x27D4EB2F;
		int PRIME5 = 0x165667B1;

	   
		int len = data.length;
		int h32;

		if (len >= 16) {
			int limit = len - 16;
			int v1 = seed + PRIME1 + PRIME2;
			int v2 = seed + PRIME2;
			int v3 = seed;
			int v4 = seed - PRIME1;

			int i = 0;
			while (i <= limit) {
				v1 = round(v1, getInt(data, i), PRIME1, PRIME2);
				i += 4;
				v2 = round(v2, getInt(data, i), PRIME1, PRIME2);
				i += 4;
				v3 = round(v3, getInt(data, i), PRIME1, PRIME2);
				i += 4;
				v4 = round(v4, getInt(data, i), PRIME1, PRIME2);
				i += 4;
			}
			h32 = rotateLeft(v1, 1) + rotateLeft(v2, 7) + rotateLeft(v3, 12) + rotateLeft(v4, 18);
		} else {
			h32 = seed + PRIME5;
		}

		h32 += len;

		int i = 0;
		while (i <= len - 4) {
			int k1 = getInt(data, i);
			h32 = rotateLeft(h32 + round(PRIME3, k1, PRIME1, PRIME2), 17) * PRIME4;
			i += 4;
		}

		while (i < len) {
			h32 = rotateLeft(h32 + (data[i] & 0xFF) * PRIME5, 11) * PRIME1;
			i++;
		}

		h32 ^= h32 >>> 15;
		h32 *= PRIME2;
		h32 ^= h32 >>> 13;
		h32 *= PRIME3;
		h32 ^= h32 >>> 16;

		return h32;
	}

	private int round(int acc, int input, int PRIME1, int PRIME2) {
		acc += input * PRIME2;
		acc = rotateLeft(acc, 13);
		acc *= PRIME1;
		return acc;
	}

	private int rotateLeft(int val, int bits) {
		return (val << bits) | (val >>> (32 - bits));
	}

	private int getInt(byte[] data, int offset) {
		return (data[offset] & 0xFF) | ((data[offset + 1] & 0xFF) << 8) | ((data[offset + 2] & 0xFF) << 16) | ((data[offset + 3] & 0xFF) << 24);
	}
	 
	public int getSize() {
		return size;
	}
	public double[][] getKeys() {
		return keys;
	}
	    	    
}
	
	 

