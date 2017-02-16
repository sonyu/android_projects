package com.androidqa;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

public class AndroidQAActivity extends Activity
	{
		// TODO: Externalize string-array
	String wheelMenu1[] = new String[]{"VIJAY 1", "VIJAY 2", "VIJAY 3", "VIJAY 4", "VIJAY 5", "VIJAY 6","VIJAY 7","VIJAY 8","VIJAY 9"};
    String wheelMenu2[] = new String[]{"age 1", "age 2", "age 3"};
    String wheelMenu3[] = new String[]{"10", "20","30","40","50","60"};

		// Wheel scrolled flag
		private boolean wheelScrolled = false;

		private TextView text;
		private EditText text1;
		private EditText text2;
		private EditText text3;
		Context  ctx;

		@Override
		public void onCreate(Bundle savedInstanceState)
			{
				super.onCreate(savedInstanceState);
				setContentView(R.layout.main);
				ctx = AndroidQAActivity.this;
				initWheel1(R.id.p1);
				initWheel2(R.id.p2);
				initWheel3(R.id.p3);

				text1 = (EditText) this.findViewById(R.id.r1);
				text2 = (EditText) this.findViewById(R.id.r2);
				text3 = (EditText) this.findViewById(R.id.r3);
				text = (TextView) this.findViewById(R.id.result);
			}

		// Wheel scrolled listener
		OnWheelScrollListener scrolledListener = new OnWheelScrollListener()
			{
				public void onScrollStarts(WheelView wheel)
					{
						wheelScrolled = true;
					}

				public void onScrollEnds(WheelView wheel)
					{
						wheelScrolled = false;
						updateStatus();
					}

				@Override
				public void onScrollingStarted(WheelView wheel) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void onScrollingFinished(WheelView wheel) {
					// TODO Auto-generated method stub
					
				}
			};

		// Wheel changed listener
		private final OnWheelChangedListener changedListener = new OnWheelChangedListener()
			{
				public void onChanged(WheelView wheel, int oldValue, int newValue)
					{
						if (!wheelScrolled)
							{
								updateStatus();
							}
					}
			};

		/**
		 * Updates entered PIN status
		 */
		private void updateStatus()
			{
				text1.setText(wheelMenu1[getWheel(R.id.p1).getCurrentItem()]);
				text2.setText(wheelMenu2[getWheel(R.id.p2).getCurrentItem()]);
				text3.setText(wheelMenu3[getWheel(R.id.p3).getCurrentItem()]);

				text.setText(wheelMenu1[getWheel(R.id.p1).getCurrentItem()] + " - " + wheelMenu2[getWheel(R.id.p2).getCurrentItem()] + " - " + wheelMenu3[getWheel(R.id.p3).getCurrentItem()]);
			}

		/**
		 * Initializes wheel
		 * 
		 * @param id
		 *          the wheel widget Id
		 */

		private void initWheel1(int id)
			{
				WheelView wheel = (WheelView) findViewById(id);
				wheel.setViewAdapter(new ArrayWheelAdapter(ctx,wheelMenu1));
				wheel.setVisibleItems(2);
				wheel.setCurrentItem(0);
				wheel.addChangingListener(changedListener);
				wheel.addScrollingListener(scrolledListener);
			}

		private void initWheel2(int id)
			{
				WheelView wheel = (WheelView) findViewById(id);
				wheel.setViewAdapter(new ArrayWheelAdapter(ctx,wheelMenu2));
				wheel.setVisibleItems(2);
				wheel.setCurrentItem(0);
				wheel.addChangingListener(changedListener);
				wheel.addScrollingListener(scrolledListener);
			}

		private void initWheel3(int id)
			{
				WheelView wheel = (WheelView) findViewById(id);
				wheel.setViewAdapter(new ArrayWheelAdapter(ctx, wheelMenu3));
				wheel.setVisibleItems(2);
				wheel.setCurrentItem(0);
				wheel.addChangingListener(changedListener);
				wheel.addScrollingListener(scrolledListener);
			}

		/**
		 * Returns wheel by Id
		 * 
		 * @param id
		 *          the wheel Id
		 * @return the wheel with passed Id
		 */
		private WheelView getWheel(int id)
			{
				return (WheelView) findViewById(id);
			}

		/**
		 * Tests wheel value
		 * 
		 * @param id
		 *          the wheel Id
		 * @param value
		 *          the value to test
		 * @return true if wheel value is equal to passed value
		 */
		private int getWheelValue(int id)
			{
				return getWheel(id).getCurrentItem();
			}
	}
