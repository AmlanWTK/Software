package com.example.testingmyentertainment.adapyer;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.testingmyentertainment.CourseModel;
import com.example.testingmyentertainment.R;

import java.util.ArrayList;

public class adapter extends BaseAdapter {

    private Context context;
    private ArrayList<CourseModel> courseModelArrayList;

    public adapter(Context context, ArrayList<CourseModel> courseModelArrayList) {
        this.context = context;
        this.courseModelArrayList = courseModelArrayList;
    }

    @Override
    public int getCount() {
        return courseModelArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return courseModelArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.course_item, parent, false);
        }

        CourseModel courseModel = courseModelArrayList.get(position);

        TextView courseName = convertView.findViewById(R.id.idTVCourse);
        ImageView courseImage = convertView.findViewById(R.id.idIVCourse);

        courseName.setText(courseModel.getCourse_name());
        courseImage.setImageResource(courseModel.getImgid());

        return convertView;
    }

    public void updateList(ArrayList<CourseModel> newList) {
        courseModelArrayList = newList;
        notifyDataSetChanged();
    }
}
