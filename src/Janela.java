import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Janela extends JFrame {
    private JButton cadastrar, mostrarCadastros, sair, limpar;
    private JTextField codigoTexto, autonomiaTexto, qntPessoasMax;
    private JTextArea mensagem;
    private ArrayList<Drone> drones;

    public Janela() {
        drones = new ArrayList<>();

        setTitle("Interface de Drones");
        setSize(600, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new GridBagLayout());
        GridBagConstraints posicao = new GridBagConstraints();
        posicao.fill = GridBagConstraints.HORIZONTAL;
        posicao.insets = new Insets(5, 5, 5, 5);

        posicao.gridx = 0;
        posicao.gridy = 0;
        posicao.gridwidth = 2;
        JLabel titulo = new JLabel("CADASTRO DE DRONES PESSOAIS", JLabel.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 20));
        add(titulo, posicao);
        posicao.gridwidth = 1;

        posicao.gridx = 0;
        posicao.gridy = 1;
        add(new JLabel("Código: "), posicao);

        posicao.gridx = 1;
        codigoTexto = new JTextField(10);
        add(codigoTexto, posicao);

        posicao.gridx = 0;
        posicao.gridy = 2;
        add(new JLabel("Quantidade de pessoas máxima: "), posicao);

        posicao.gridx = 1;
        qntPessoasMax = new JTextField(10);
        add(qntPessoasMax, posicao);

        posicao.gridx = 0;
        posicao.gridy = 3;
        add(new JLabel("Autonomia: "), posicao);

        posicao.gridx = 1;
        autonomiaTexto = new JTextField(10);
        add(autonomiaTexto, posicao);

        posicao.gridx = 0;
        posicao.gridy = 6;
        posicao.gridwidth = 2;
        cadastrar = new JButton("Cadastrar");
        add(cadastrar, posicao);
        cadastrar.addActionListener(e -> cadastrarDrone());

        posicao.gridy = 7;
        mostrarCadastros = new JButton("Mostrar Cadastros");
        add(mostrarCadastros, posicao);
        mostrarCadastros.addActionListener(e -> mostrarDronesCadastrados());

        posicao.gridy = 8;
        limpar = new JButton("Limpar");
        add(limpar, posicao);
        limpar.addActionListener(e -> limparCampos());

        posicao.gridy = 9;
        sair = new JButton("Sair");
        add(sair, posicao);
        sair.addActionListener(e -> saida());

        posicao.gridy = 10;
        mensagem = new JTextArea(15, 40);
        mensagem.setEditable(false);
        mensagem.setLineWrap(true);
        mensagem.setWrapStyleWord(true);
        JScrollPane scrollPane = new JScrollPane(mensagem);
        add(scrollPane, posicao);
    }

    public void limparCampos() {
        codigoTexto.setText("");
        autonomiaTexto.setText("");
        qntPessoasMax.setText("");
        mensagem.setText("");
    }

    private void mostrarDronesCadastrados() {
        if (drones.isEmpty()) {
            mensagem.setText("Nenhum drone cadastrado.");
        } else {
            StringBuilder sb = new StringBuilder();
            for (Drone drone : drones) {
                sb.append(drone).append("\n");
            }
            mensagem.setText(sb.toString());
        }
    }

    public void cadastrarDrone() {
        try {
            int codigo = Integer.parseInt(codigoTexto.getText());
            double pesoMax = Double.parseDouble(qntPessoasMax.getText());
            double autonomia = Double.parseDouble(autonomiaTexto.getText());

            for (Drone drone : drones) {
                if (drone.getCodigo() == codigo) {
                    mensagem.setText("ERRO: Já existe um drone cadastrado com este código");
                    return;
                }
            }

            Drone novoDrone = new Drone(codigo, pesoMax, autonomia);
            drones.add(novoDrone);
            mensagem.setText("Drone cadastrado com sucesso!");

        } catch (NumberFormatException ex) {
            mensagem.setText("Erro: Verifique se todos os campos numéricos estão preenchidos corretamente.");
        }
    }

    public void saida() {
        System.exit(0);
    }
}
