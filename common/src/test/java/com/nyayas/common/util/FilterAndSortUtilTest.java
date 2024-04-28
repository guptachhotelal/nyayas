package com.nyayas.common.util;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;
import java.util.Collection;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;

class FilterAndSortUtilTest {

    @Test
    void testInstantiation() throws Exception {
	Constructor<FilterAndSortUtil> constructor = FilterAndSortUtil.class.getDeclaredConstructor();
	assertTrue(Modifier.isPrivate(constructor.getModifiers()));
	InvocationTargetException ite = assertThrows(InvocationTargetException.class, () -> {
	    constructor.setAccessible(true);
	    constructor.newInstance();
	});
	Exception ex = (UnsupportedOperationException) ite.getTargetException();
	assertTrue(ex.getMessage().contains("Cannot"));
    }

    @Test
    void testFilter() {
	List<TestData> list = temp(new Random().nextInt(1, 10));
	list.add(TestData.builder().city("Mumbai").build());
	list.add(TestData.builder().state("Delhi").build());
	Collection<TestData> filtered = FilterAndSortUtil.filter(list, "Delhi");
	assertFalse(filtered.isEmpty());
    }

    @Test
    void testSortAsc() {
	Random random = new Random();
	List<TestData> list = temp(random.nextInt(1, 10));
	Collection<TestData> filtered = FilterAndSortUtil.sort(list, columnName(random), true);
	assertFalse(filtered.isEmpty());
    }

    @Test
    void testSortDesc() {
	Random random = new Random();
	List<TestData> list = temp(random.nextInt(1, 10));
	Collection<TestData> filtered = FilterAndSortUtil.sort(list, columnName(random), false);
	assertFalse(filtered.isEmpty());
    }

    private List<TestData> temp(int seed) {
	return Stream.generate(TestData.builder()::build).limit(seed).collect(Collectors.toList());
    }

    private String columnName(Random random) {
	Field[] fields = TestData.class.getDeclaredFields();
	return fields[random.nextInt(fields.length)].getName();
    }
}
