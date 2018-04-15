package onboardinganimation.ketki.com.onboardinganim;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

/**
 * Created by gouravbansal on 24/08/16.
 */
public class OnBoardingBottomTextAdapter extends BaseAdapter {

    private LayoutInflater inflater;
    private String[] titles = new String[]{"Activities", "Destinations", "Salons", "Movies", "Spas", "Restaurants"};

    OnBoardingBottomTextAdapter(Context context) {
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return titles.length;
    }

    @Override
    public Object getItem(int position) {
        return titles[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View rowView = convertView;
        ViewHolderOffer viewHolderOffer;
        if (rowView == null) {
            rowView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_onboarding_bottom_tile, parent, false);
            viewHolderOffer = new ViewHolderOffer(rowView);
            rowView.setTag(viewHolderOffer);
        } else {
            viewHolderOffer = (ViewHolderOffer) rowView.getTag();
        }
        viewHolderOffer.txtFeedBackOption.setText(titles[position]);
        return rowView;
    }


    public class ViewHolderOffer extends RecyclerView.ViewHolder {
        public TextView txtFeedBackOption;

        public ViewHolderOffer(View v) {
            super(v);
            txtFeedBackOption = (TextView) v.findViewById(R.id.tv_tiles);
        }
    }
}
