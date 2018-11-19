package edu.yu.cs.ds.finalproject;

import java.util.Objects;

import edu.yu.cs.dataStructures.fall2016.SimpleSQLParser.*;
import edu.yu.cs.dataStructures.fall2016.SimpleSQLParser.ColumnDescription.DataType;

public class DataEntry {
	public ColumnValuePair colValPair;
	public ColumnDescription columnDescription;
	public String value;
	public String columnName;
	
	/**
	 * constructs a data entry from a column value pair
	 * @param colValPair
	 */
	public DataEntry(ColumnValuePair colValPair) {
		this.value = colValPair.getValue();
		this.columnName = colValPair.getColumnID().getColumnName();
	}
	
	/**
	 * Constructs a data entry and sets the value to the default if there is one
	 * @param defaultValue
	 * @param cd
	 */
	public DataEntry(String defaultValue, ColumnDescription cd) {
		this.value = defaultValue;
		if(cd != null ) {
			this.columnName = cd.getColumnName();
		}
	}
	
	/**
	 * This method checks that the data entry fits with the conditions of its column
	 * @param columnDescription
	 */
	public void performValueChecks(ColumnDescription columnDescription) {
		this.dataTypeCheck(columnDescription.getColumnType());
		this.isRightLength(columnDescription);
	}
	
	/**
	 * Ensures a value is of the proper data type
	 * @param columnType
	 */
	public void dataTypeCheck(DataType columnType) {
		String value = this.value;
		try {
			if (columnType.equals(DataType.INT)) {
				Integer.parseInt(value);
			}
			if (columnType.equals(DataType.DECIMAL)) {
				Double.parseDouble(value);
			}	
		} catch (NumberFormatException e) {
			throw new IllegalArgumentException("Improper data type for a column");
		}
		if (columnType.equals(DataType.BOOLEAN)) {
			if (!(value.toLowerCase().startsWith("true") || value.toLowerCase().startsWith("false"))) {
				throw new IllegalArgumentException("Improper data type for a column");
			}
		}
	}
	
	/**
	 * This method ensures that varchars, integers, and decimals are not longer than the specified length
	 * @param columnDescription
	 */
	public void isRightLength(ColumnDescription columnDescription) {
		String value = this.value;
		if (columnDescription.getColumnType().equals(DataType.INT)) {
			if ((columnDescription.getWholeNumberLength() < value.length()) && (columnDescription.getWholeNumberLength() != 0)) {
				throw new IllegalArgumentException("Your whole number is too long");
			}
		}
		if (columnDescription.getColumnType().equals(DataType.DECIMAL)) {
			char[] letters = value.toCharArray();
			int predecimal = 0;
			for(int i = 0; i < letters.length; i++) {
				if(letters[i] == '.') {
					break;
				}
				predecimal++;
			}
			int postdecimal = letters.length - predecimal - 1;
			if ((columnDescription.getWholeNumberLength() < predecimal) && (columnDescription.getWholeNumberLength() != 0)) {
				throw new IllegalArgumentException("Your whole number is too long");
			}
			if ((columnDescription.getFractionLength() < postdecimal) && (columnDescription.getFractionLength() != 0)) {
				throw new IllegalArgumentException("Your fractional number is too long");
			}
		}
		if ((columnDescription.getColumnType().equals(DataType.VARCHAR)) && (columnDescription.getWholeNumberLength() != 0)) {
			if (columnDescription.getVarCharLength() < value.length()) {
				throw new IllegalArgumentException("Your varchar is too long");
			}
		}
	}
	
	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null) {
			return false;
		}
		if (getClass() != o.getClass()) {
			return false;
		}
		DataEntry otherDataEntry= (DataEntry) o;
		return Objects.equals(this.value, otherDataEntry.value);
	}
}
