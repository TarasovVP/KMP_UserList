//
// Created by Volodymyr Tarasov on 10.09.2025.
//

import Foundation

import shared

@MainActor
final class UsersViewModel: ObservableObject {
    @Published var users: [User] = []
    @Published var isLoading = false
    @Published var error: String?

    private let useCase: GetUserListUseCase

    init(useCase: GetUserListUseCase = GetUserListUseCase()) {
        self.useCase = useCase
    }

    func refresh() {
        isLoading = true
        error = nil

      
        useCase.execute { result, err in
            Task { @MainActor in
                self.isLoading = false
                if let err {
                    self.error = err.localizedDescription
                    self.users = []
                    return
                }

                if let swiftArray = result {
                    self.users = swiftArray
                    return
                }

                self.users = []
            }
        }
    }
}
