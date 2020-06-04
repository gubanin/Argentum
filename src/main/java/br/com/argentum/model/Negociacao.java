package br.com.argentum.model;

import java.time.LocalDateTime;

public final class Negociacao {

	private final Double preco;
	private final Integer quantidade;
	private final LocalDateTime data;
	
	public Negociacao(Double preco, Integer quantidade, LocalDateTime data) {
		if(quantidade < 1) {
			throw new IllegalArgumentException("A quantidade deve ser positiva");
		}
		
		if(data == null) {
			throw new IllegalArgumentException("A data nao pode ser nula");
		}
		
		if(preco < 0) {
			throw new IllegalArgumentException("O preco nao pode ser negativo");
		}
		this.preco = preco;
		this.quantidade = quantidade;
		this.data = data;
	}
	
	public Double getPreco() {
		return preco;
	}
	
	public Integer getQuantidade() {
		return quantidade;
	}
	
	public LocalDateTime getData() {
		return data;
	}
	
	public double getVolume() {
		return this.preco * this.quantidade;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((data == null) ? 0 : data.hashCode());
		result = prime * result + ((preco == null) ? 0 : preco.hashCode());
		result = prime * result + ((quantidade == null) ? 0 : quantidade.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Negociacao other = (Negociacao) obj;
		if (data == null) {
			if (other.data != null)
				return false;
		} else if (!data.equals(other.data))
			return false;
		if (preco == null) {
			if (other.preco != null)
				return false;
		} else if (!preco.equals(other.preco))
			return false;
		if (quantidade == null) {
			if (other.quantidade != null)
				return false;
		} else if (!quantidade.equals(other.quantidade))
			return false;
		return true;
	}

	public boolean isMesmoDia(LocalDateTime outraData) {
		return this.data.getDayOfMonth() == outraData.getDayOfMonth() &&
				this.data.getMonth() == outraData.getMonth() &&
				this.data.getYear() == outraData.getYear();
	}
}
