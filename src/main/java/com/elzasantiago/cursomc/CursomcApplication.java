package com.elzasantiago.cursomc;

import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.elzasantiago.cursomc.domain.Categoria;
import com.elzasantiago.cursomc.domain.Cidade;
import com.elzasantiago.cursomc.domain.Cliente;
import com.elzasantiago.cursomc.domain.Endereco;
import com.elzasantiago.cursomc.domain.Estado;
import com.elzasantiago.cursomc.domain.ItemPedido;
import com.elzasantiago.cursomc.domain.Pagamento;
import com.elzasantiago.cursomc.domain.PagamentoComBoleto;
import com.elzasantiago.cursomc.domain.PagamentoComCartao;
import com.elzasantiago.cursomc.domain.Pedido;
import com.elzasantiago.cursomc.domain.Produto;
import com.elzasantiago.cursomc.domain.enums.EstadoPagamento;
import com.elzasantiago.cursomc.domain.enums.TipoCliente;
import com.elzasantiago.cursomc.repositories.CategoriaRepository;
import com.elzasantiago.cursomc.repositories.CidadeRepository;
import com.elzasantiago.cursomc.repositories.ClienteRepository;
import com.elzasantiago.cursomc.repositories.EnderecoRepository;
import com.elzasantiago.cursomc.repositories.EstadoRepository;
import com.elzasantiago.cursomc.repositories.ItemPedidoRepository;
import com.elzasantiago.cursomc.repositories.PagamentoRepository;
import com.elzasantiago.cursomc.repositories.PedidoRepository;
import com.elzasantiago.cursomc.repositories.ProdutoRepository;

@SpringBootApplication
public class CursomcApplication implements CommandLineRunner{

	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Autowired
	private EstadoRepository estadoRepository;
	
	@Autowired
	private CidadeRepository cidadeRepository;
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private EnderecoRepository enderecoRepository;
	
	@Autowired
	private PedidoRepository pedidoRepository;
	
	@Autowired
	private PagamentoRepository pagamentoRepository;
	
	@Autowired
	private ItemPedidoRepository itemPedidoRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(CursomcApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
	    Categoria cat1 = new Categoria(null,"Informática");
		Categoria cat2 = new Categoria(null,"Escritório");
		Categoria cat3 = new Categoria(null,"Eletrodomestico");
		Categoria cat4 = new Categoria(null,"Cama, Mes e Banho");		
		Categoria cat5 = new Categoria(null,"Eletronicos");		
		Categoria cat6 = new Categoria(null,"Decoração");					
		Categoria cat7 = new Categoria(null,"Vestuário");		
		Categoria cat8 = new Categoria(null,"Jardinagem");
		Categoria cat9 = new Categoria(null,"Perfumaria");
		Categoria cat10 = new Categoria(null,"Porcelanas");			
		Categoria cat11 = new Categoria(null,"Cristais");
		Categoria cat12 = new Categoria(null,"Faqueiro");	
		
		Produto p1 = new Produto(null, "Computador", 2000.00);
		Produto p2 = new Produto(null, "Impressora", 800.00);
		Produto p3 = new Produto(null, "Mouse", 80.00);
		
		cat1.getProdutos().addAll(Arrays.asList(p1, p2, p3));
		cat2.getProdutos().addAll(Arrays.asList(p2));
		
		p1.getCategorias().addAll(Arrays.asList(cat1));
		p2.getCategorias().addAll(Arrays.asList(cat1, cat2));
		p3.getCategorias().addAll(Arrays.asList(cat1));
		
		categoriaRepository.saveAll(Arrays.asList(cat1, cat2, cat3, cat4, cat5, cat6,
				cat7, cat8, cat9, cat10, cat11, cat12));
		produtoRepository.saveAll(Arrays.asList(p1, p2, p3));
		
		Estado est1 = new Estado(null, "Rio de Janeiro");
		Estado est2 = new Estado(null, "São Paulo");
		
		Cidade c1 = new Cidade(null, "Niteroi", est1);
		Cidade c2 = new Cidade(null, "São Paulo", est2);
		Cidade c3 = new Cidade(null, "Campinas", est2);
		
		est1.getCidades().addAll(Arrays.asList(c1));
		est2.getCidades().addAll(Arrays.asList(c2, c3));
		
		estadoRepository.saveAll(Arrays.asList(est1, est2));
		cidadeRepository.saveAll(Arrays.asList(c1, c2, c3));
		
		Cliente cli1 = new Cliente(null, "Elza Maria Novais Santiago", "elzansantiago@gmail.com", "10490175520", TipoCliente.PESSOAFISICA);
		
		cli1.getTelefones().addAll(Arrays.asList("976567670", "986837354"));
		
		Endereco e1 = new Endereco(null, "Rua Mem de Sá", "82", "apto.402", "Icaraí", "24220.261", cli1, c1 );
		Endereco e2 = new Endereco(null, "Avenida Mattos", "105", "Sala 402", "Centro", "24220.261", cli1, c2 );
			
		clienteRepository.saveAll(Arrays.asList(cli1));
		enderecoRepository.saveAll(Arrays.asList(e1, e2));
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		
		Pedido ped1 = new Pedido(null, sdf.parse("30/09/2017 10:32"), cli1, e1);
		Pedido ped2 = new Pedido(null, sdf.parse("10/10/2017 19:35"), cli1, e2);
		
		Pagamento pagto1 = new PagamentoComCartao(null, EstadoPagamento.QUITADO, ped1, 6);
		ped1.setPagamento(pagto1);
		
		Pagamento pagto2 = new PagamentoComBoleto(null, EstadoPagamento.PENDENTE, ped2, sdf.parse("20/10/2017 00:00"), null);
		ped2.setPagamento(pagto2);
		
		cli1.getPedidos().addAll(Arrays.asList(ped1, ped2));
				
		pedidoRepository.saveAll(Arrays.asList(ped1, ped2));
		pagamentoRepository.saveAll(Arrays.asList(pagto1, pagto2));
		
		ItemPedido ip1 = new ItemPedido(ped1, p1,   0.00, 1, 2000.00);
		ItemPedido ip2 = new ItemPedido(ped1, p3,   0.00, 2,   00.00);
		ItemPedido ip3 = new ItemPedido(ped2, p2, 100.00, 1,  800.00);
		
		ped1.getItens().addAll(Arrays.asList(ip1, ip2));
		ped2.getItens().addAll(Arrays.asList(ip3));
		
		p1.getItens().addAll(Arrays.asList(ip1));
		p2.getItens().addAll(Arrays.asList(ip3));
		p3.getItens().addAll(Arrays.asList(ip2));
		
		itemPedidoRepository.saveAll(Arrays.asList(ip1, ip2, ip3));
		
		/*itemPedidoRepository.saveAll(Arrays.asList(ip1, ip2, ip3));*/
		
	}

	
}

