package codigo.labplc.mx.traxi.buscarplaca.paginador.paginas;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.AttributeSet;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;
import android.widget.Toast;
import codigo.labplc.mx.traxi.R;
import codigo.labplc.mx.traxi.R.color;
import codigo.labplc.mx.traxi.buscarplaca.bean.AutoBean;
import codigo.labplc.mx.traxi.buscarplaca.paginador.paginas.termometro.ShieldView;
import codigo.labplc.mx.traxi.buscarplaca.paginador.paginas.termometro.ThermometerView;
import codigo.labplc.mx.traxi.dialogos.Descripcion_Escudo;
import codigo.labplc.mx.traxi.dialogos.Dialogos;
import codigo.labplc.mx.traxi.fonts.fonts;

public class Datos extends View {

	private TextView marca,submarca,modelo;
	private LinearLayout container;
	private View view;
	private Activity context;
	private AutoBean autoBean;
	
	
	public Datos(Activity context) {
		super(context);
		this.context=context;
	}
	public Datos(Activity context, AttributeSet attrs) {
		super(context, attrs);
		this.context=context;
	}
	@SuppressLint("Instantiatable")
	public Datos(Activity context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		this.context=context;
	}

	public void init(AutoBean autoBean){
		this.autoBean=autoBean;
		init();
	}
	
	
	public void init(){

		
		LayoutInflater inflater = (LayoutInflater)   getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE); 
		view = inflater.inflate(R.layout.activity_datos, null);
		
		((TextView) view.findViewById(R.id.datos_tv_niveles_confianza)).setTypeface(new fonts(context).getTypeFace(fonts.FLAG_MAMEY));	
		((TextView) view.findViewById(R.id.datos_tv_niveles_confianza)).setTextColor(new fonts(context).getColorTypeFace(fonts.FLAG_GRIS_OBSCURO));
		
	//	((TextView) view.findViewById(R.id.datos_tv_calif_usuario)).setTypeface(new fonts(context).getTypeFace(fonts.FLAG_MAMEY));	
	//	((TextView) view.findViewById(R.id.datos_tv_calif_usuario)).setTextColor(new fonts(context).getColorTypeFace(fonts.FLAG_AMARILLO));
		
	//	((TextView) view.findViewById(R.id.datos_tv_titulo_desc)).setTypeface(new fonts(context).getTypeFace(fonts.FLAG_GRIS_CLARO));	
		//((TextView) view.findViewById(R.id.datos_tv_titulo_desc)).setTextColor(new fonts(context).getColorTypeFace(fonts.FLAG_GRIS_OBSCURO));
		
		
		((TextView) view.findViewById(R.id.datos_general_tv_titulo_principal)).setTypeface(new fonts(context).getTypeFace(fonts.FLAG_MAMEY));	
		((TextView) view.findViewById(R.id.datos_general_tv_titulo_principal)).setTextColor(new fonts(context).getColorTypeFace(fonts.FLAG_GRIS_OBSCURO));
		

		TextView datos_tv_titulo = (TextView)view.findViewById(R.id.datos_tv_titulo);
		datos_tv_titulo.setTypeface(new fonts(context).getTypeFace(fonts.FLAG_GRIS_CLARO));
		datos_tv_titulo.setTextColor(new fonts(context).getColorTypeFace(fonts.FLAG_GRIS_OBSCURO));
		datos_tv_titulo.append(" "+autoBean.getPlaca());
		
		marca = (TextView)view.findViewById(R.id.datos_tv_marca);
		marca.setTypeface(new fonts(context).getTypeFace(fonts.FLAG_MAMEY));
		marca.setTextColor(new fonts(context).getColorTypeFace(fonts.FLAG_GRIS_OBSCURO));
		
	
	//	descripcion =(TextView)view.findViewById(R.id.datos_tv_descripcion);
	//	descripcion.setTypeface(new fonts(context).getTypeFace(fonts.FLAG_MAMEY));
	//	descripcion.setTextColor(new fonts(context).getColorTypeFace(fonts.FLAG_GRIS_OBSCURO));
		
		container = (LinearLayout)view.findViewById(R.id.Thermometer_Container);
	//	container_usuario = (LinearLayout)view.findViewById(R.id.Thermometer_Container_usuarios);
	
		marca.setText(autoBean.getMarca()+", ");
		marca.append(autoBean.getSubmarca()+", ");
		marca.append(autoBean.getAnio());
		
		if(marca.getText().toString().equals(", , ")){
			marca.setText(getResources().getString(R.string.adeudos_row_no_hay_datos));
		}
		
	//	descripcion.setText(autoBean.getDescripcion_calificacion_app());
		
		crearTermometro();

		
	}

	
	@SuppressLint("ResourceAsColor")
	public void crearTermometro(){
		
		LinearLayout verticalLayout = new LinearLayout(context);
		verticalLayout.setOrientation(LinearLayout.VERTICAL);
		LayoutParams params = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT,1);
		params.gravity=Gravity.CENTER;
		verticalLayout.setLayoutParams(params);
		 
		final ShieldView shield = new ShieldView(context);
		Display display = context.getWindowManager().getDefaultDisplay(); 
		int actionBarHeight = context.getActionBar().getHeight();
		int newProgress = shield.getProgressWithJump(autoBean.getCalificacion_final(), ThermometerView.JUMP_PROGRESS_ANIMATION); // Progress with jump
		int size= display.getHeight()-actionBarHeight;
		if(newProgress<=30){
			shield.initUI(size/2,size/2,R.color.rojo_logo);
			}else if(newProgress <=70 && newProgress>30){
				shield.initUI(size/2,size/2,R.color.generic_amarillo);
			}else if(newProgress>70){
				shield.initUI(size/2,size/2,R.color.android_green);
			}
		
		shield.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(context,Descripcion_Escudo.class);
				intent.putExtra("descripcion_escudo", autoBean.getDescripcion_calificacion_app());
				context.startActivity(intent);
			//	Dialogos.Toast(context, autoBean.getDescripcion_calificacion_app(), Toast.LENGTH_LONG);	
			}
		});
	
		
		
		shield.setProgress(newProgress);
		verticalLayout.addView(shield);
		container.addView(verticalLayout);
		
		
	}
	
	public View getView(){
		return view;
	}
	
	
	

}
