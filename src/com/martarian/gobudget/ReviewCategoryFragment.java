package com.martarian.gobudget;

import java.util.UUID;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import org.achartengine.ChartFactory;
import org.achartengine.chart.PointStyle;
import org.achartengine.model.XYMultipleSeriesDataset;
import org.achartengine.model.XYSeries;
import org.achartengine.renderer.BasicStroke;
import org.achartengine.renderer.XYMultipleSeriesRenderer;
import org.achartengine.renderer.XYSeriesRenderer;


import android.graphics.Color;
import android.graphics.Paint.Align;
import android.graphics.Typeface;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ReviewCategoryFragment extends Fragment{
	public static final String REVIEW_EXTRA_CATEGORY_ID = "gobudget.CATEGORY_ID";
	private View mChart;
	LinearLayout chartContainer;
	private String[] mMonth = new String[] {
	"Jan", "Feb" , "Mar", "Apr", "May", "Jun",
	"Jul", "Aug" , "Sep", "Oct", "Nov", "Dec"
	};

	Category mReviewCategory;
	TextView mReview_Category_title;
	public static ReviewCategoryFragment newInstance(UUID ReviewCategoryId){
		Bundle args = new Bundle();
		args.putSerializable(REVIEW_EXTRA_CATEGORY_ID, ReviewCategoryId);
		ReviewCategoryFragment fragment = new ReviewCategoryFragment();
		fragment.setArguments(args);
		return fragment;
	}
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		UUID ReviewcategoryId = (UUID)getArguments().getSerializable(REVIEW_EXTRA_CATEGORY_ID);
		mReviewCategory = CategoryLab.get(getActivity()).getCategory(ReviewcategoryId);
	}
	
	public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstaceState) {
		View v = inflater.inflate(R.layout.fragment_view_budget, parent, false);
		mReview_Category_title = (TextView)v.findViewById(R.id.category_name);
		mReview_Category_title.setText(mReviewCategory.getTitle());
		
			int[] x = { 0,1,2,3,4,5,6,7, 8, 9, 10, 11 };
			int[] budget = { 2000,2500,2700,3000,2800,3500,3700,3800, 0,0,0,0};
			int[] expense = {2200, 2700, 2900, 2800, 2600, 3000, 3300, 3400, 0, 0, 0, 0 };
			
			
			
			// Creating an XYSeries for budget and expense
			XYSeries budgetSeries = new XYSeries("Budget");
			XYSeries expenseSeries = new XYSeries("Expense");
			
			// Adding data to budget and Expense Series
			for(int i=0;i<x.length;i++){
				budgetSeries.add(i,budget[i]);
			expenseSeries.add(i,expense[i]);
			}

			// Creating a dataset to hold each series
			XYMultipleSeriesDataset dataset = new XYMultipleSeriesDataset();
			dataset.addSeries(budgetSeries);
			dataset.addSeries(expenseSeries);

			// Creating XYSeriesRenderer to customize budgetSeries
			XYSeriesRenderer budgetRenderer = new XYSeriesRenderer();
			budgetRenderer.setColor(Color.RED); //color of the graph set to red
			budgetRenderer.setFillPoints(true);
			budgetRenderer.setLineWidth(2f);
			budgetRenderer.setDisplayChartValues(true);
			budgetRenderer.setDisplayChartValuesDistance(10);
			budgetRenderer.setPointStyle(PointStyle.CIRCLE);
			budgetRenderer.setStroke(BasicStroke.SOLID); 

			// Creating XYSeriesRenderer to customize expenseSeries
			XYSeriesRenderer expenseRenderer = new XYSeriesRenderer();
			expenseRenderer.setColor(Color.GREEN);
			expenseRenderer.setFillPoints(true);
			expenseRenderer.setLineWidth(2f);
			expenseRenderer.setDisplayChartValues(true);
			expenseRenderer.setPointStyle(PointStyle.SQUARE);
			expenseRenderer.setStroke(BasicStroke.SOLID); 

			// Creating a XYMultipleSeriesRenderer to customize the whole chart
			XYMultipleSeriesRenderer multiRenderer = new XYMultipleSeriesRenderer();
			multiRenderer.setXLabels(0);
			multiRenderer.setXTitle("Year 2014");
			multiRenderer.setYTitle("Amount in Dollars");

			/***
			* Customizing graphs
			*/
			multiRenderer.setChartTitleTextSize(28);
			multiRenderer.setAxisTitleTextSize(24);
			multiRenderer.setLabelsTextSize(20);
			multiRenderer.setZoomButtonsVisible(false);
			multiRenderer.setPanEnabled(false, false);
			multiRenderer.setClickEnabled(false);
			multiRenderer.setZoomEnabled(false, false);
			multiRenderer.setShowGridY(true);
			multiRenderer.setShowGridX(true);
			multiRenderer.setFitLegend(true);
			multiRenderer.setShowGrid(true);
			multiRenderer.setZoomEnabled(false);
			multiRenderer.setExternalZoomEnabled(false);
			multiRenderer.setAntialiasing(true);
			multiRenderer.setInScroll(false);
			multiRenderer.setLegendHeight(30);
			multiRenderer.setXLabelsAlign(Align.CENTER);
			multiRenderer.setYLabelsAlign(Align.LEFT);
			multiRenderer.setTextTypeface("sans_serif", Typeface.NORMAL);
			multiRenderer.setYLabels(10);
			multiRenderer.setYAxisMax(4000);
			multiRenderer.setXAxisMin(-0.5);
			multiRenderer.setXAxisMax(11);
			multiRenderer.setBackgroundColor(Color.TRANSPARENT);
			multiRenderer.setMarginsColor(getResources().getColor(R.color.transparent_background));
			multiRenderer.setApplyBackgroundColor(true);
			multiRenderer.setScale(2f);
			multiRenderer.setPointSize(4f);
			multiRenderer.setMargins(new int[]{30, 30, 30, 30});

			for(int i=0; i< x.length;i++){
			multiRenderer.addXTextLabel(i, mMonth[i]);
			}

			// Adding budgetRenderer and expenseRenderer to multipleRenderer
			// Note: The order of adding dataseries to dataset and renderers to multipleRenderer
			// should be same
			multiRenderer.addSeriesRenderer(budgetRenderer);
			multiRenderer.addSeriesRenderer(expenseRenderer);
			chartContainer = (LinearLayout)v.findViewById(R.id.chart);

			//remove any views before u paint the chart
			chartContainer.removeAllViews();
			//drawing bar chart
			mChart = ChartFactory.getLineChartView(getActivity(), dataset, multiRenderer);
			//adding the view to the linearlayout
			chartContainer.addView(mChart);
						
			return v;
	}

}