package collioni.douglas.tarefascustom;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by lacomp01 on 10/09/2016.
 */
public class TarefaAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<Tarefa> tarefas;

    public TarefaAdapter(Context context, ArrayList<Tarefa> tarefas) {
        this.context = context;
        this.tarefas = tarefas;
    }

    @Override
    public int getCount() {
        return tarefas.size();
    }

    @Override
    public Object getItem(int position) {
        return tarefas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // declara um objeto ViewHolder
        ViewHolder viewHolder;

        // testa se o convertView é nulo
        if (convertView == null) {
            // pega o layout inflater
            LayoutInflater inflater = ((Activity)context).getLayoutInflater();

            // carrega o convertView com o layout da lista
            convertView = inflater.inflate(
                    R.layout.list_item,
                    parent,
                    false);

            // cria o viewHolder passando o convertView
            viewHolder = new ViewHolder(convertView);

            // configura a tag do convertView para o viewHolder
            convertView.setTag(viewHolder);
        }
        // senão
        else {
            // pega o viewHolder do convertView já carregado
            viewHolder = (ViewHolder)convertView.getTag();
        }

        // pega a tarefa na posição atual
        Tarefa tarefa = tarefas.get(position);

        // testa se a tarefa não é nula
        if (tarefa != null) {

            // pega as propriedades do objeto tarefa
            String descricao = tarefa.getDescricao();
            String prioridade = tarefa.getPrioridade();

            // popula os componentes do layout com as propriedades da tarefa
            viewHolder.tvDescricao.setText(descricao);
            viewHolder.tvPrioridade.setText(prioridade);

            int img = 0;

            // seleciona a imagem correta dependendo da prioridade da tarefa
            if (prioridade.equalsIgnoreCase("alta")) {
                img = R.drawable.vermelho;
            } else if (prioridade.equalsIgnoreCase("média")) {
                img = R.drawable.amarelo;
            } else if (prioridade.equalsIgnoreCase("baixa")) {
                img = R.drawable.verde;
            }

            // popula o componente imageview com a imagem correta
            viewHolder.ivImagem.setImageResource(img);
        }

        // retorna o convertView
        return convertView;
    }

    public static class ViewHolder {

        // mapeia os componentes do layout da lista
        public final TextView tvDescricao, tvPrioridade;
        public final ImageView ivImagem;

        public ViewHolder(View view) {

            // carrega os componentes do layout pelo ID
            tvDescricao = (TextView)
                    view.findViewById(R.id.tv_descricao);

            tvPrioridade = (TextView)
                    view.findViewById(R.id.tv_prioridade);

            ivImagem = (ImageView)
                    view.findViewById(R.id.iv_imagem);
        }
    }
}
