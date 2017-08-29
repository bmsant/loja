package com.example.demo;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="Vendas")
public class Venda {
	@Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SEQ_VENDA")
    @SequenceGenerator(name="SEQ_VENDA", sequenceName="SEQ_VENDA", initialValue = 1, allocationSize = 1)
	private int id;
	//@Column(columnDefinition="DATE")
	//private LocalDate data;
	@OneToMany(fetch=FetchType.EAGER, cascade= {CascadeType.PERSIST,CascadeType.MERGE})
	@JoinColumn(name = "venda_id")
	private List<ItemDeVenda> itensDeVenda;
	
	public int getId() {
		return id;
	}
	
	public List<ItemDeVenda> getItensDeVenda() {
		return itensDeVenda;
	}
	public void vender(Produto prod, int quant) {
		ItemDeVenda item = new ItemDeVenda();
		item.setProduto(prod);
		item.setQuantidade(quant);
		itensDeVenda.add(item);
	}
	public double getTotal() {
		double soma = 0.0;
		for(ItemDeVenda item : itensDeVenda) {
			soma += item.getSubTotal();
		}
		return soma;
	}
}
