class MergeSort:
    def mergesort(self, list):
        return self._mergesort(list, len(list))

    def _mergesort(self, list, size):
        if size < 2:
            return
        
        middle = size // 2
        left = list[:middle]
        right = list[middle:]
        
        self._mergesort(left, len(left))
        self._mergesort(right, len(right))
        return self._merge(list, left, right)

    def _merge(self, list, left, right):
        list_index = 0
        l_index = 0
        r_index = 0

        while l_index < len(left) and r_index < len(right):
            if left[l_index] < right[r_index]:
                list[list_index] = left[l_index]
                l_index += 1
            else:
                list[list_index] = right[r_index]
                r_index += 1    
            list_index += 1

        while l_index < len(left):
            list[list_index] = left[l_index]
            l_index += 1
            list_index += 1

        while r_index < len(right):
            list[list_index] = right[r_index]
            r_index += 1
            list_index += 1

        return list

# Test
m = MergeSort()
# list = [1, 3, 4, 2, 6, 5, 100, 9]
list = [1, 4, 2, 6, 5, 100, 9, 3]
# list = [1, 4, 2, 9, 3]
print('Before:', list)
list = m.mergesort(list)
print('After: ', list)
list.sort()
print('Answer: ', list)