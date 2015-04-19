package free.hindi.aarti;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;


public class AartiAdapter extends ArrayAdapter<String> {

    protected Context mContext;

    String[] mAarti;
    int[] mGodImages;

    public AartiAdapter(Context context, int[] godImages, String[] aarti) {
        super(context, R.layout.grid_item, aarti);
        mContext = context;
        mGodImages = godImages;
        mAarti = aarti;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.grid_item, null);
            holder = new ViewHolder();
            holder.mGodImage = (ImageView) convertView.findViewById(R.id.god_image);
            holder.mGodName = (TextView) convertView.findViewById(R.id.god_text);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.mGodImage.setImageResource(mGodImages[position]);
        holder.mGodName.setText(mAarti[position]);

        return convertView;
    }

    private static class ViewHolder {
        ImageView mGodImage;
        TextView mGodName;
    }

}