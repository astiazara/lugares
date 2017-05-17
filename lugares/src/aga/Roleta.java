package aga;

import java.util.ArrayList;
import java.util.List;

public class Roleta<T> {
	
	private List<T> itens = new ArrayList<T>();
	
	public int size(){
		return itens.size();
	}
	
	public void adicionar(T item, int quantidade){
		for(int i=0; i<quantidade; i++){
			itens.add(item);
		}
	}
	
	public T girar(){
		if(itens.isEmpty())
			return null;
		else
			return itens.get(Rand.random.nextInt(itens.size()));
	}
	
}
