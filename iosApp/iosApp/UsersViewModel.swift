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

    private var useCase: GetUserListUseCase?

    init(useCase: GetUserListUseCase? = nil) {
        self.useCase = useCase
    }

    func refresh() {
        isLoading = true
        error = nil

        func run(with uc: GetUserListUseCase) {
            uc.execute { result, err in
                Task { @MainActor in
                    self.isLoading = false
                    if let err = err {
                        self.error = err.localizedDescription
                        self.users = []
                        return
                    }
                    if let arr = result {
                        self.users = arr
                        return
                    }
                    self.users = []
                }
            }
        }

        if let uc = useCase {
            run(with: uc)
            return
        }

        BridgeKt.getUserListUseCase { resolved, bridgeErr in
            if let resolved = resolved {
                self.useCase = resolved
                run(with: resolved)
            } else {
                Task { @MainActor in
                    self.isLoading = false
                    self.error = bridgeErr?.localizedDescription ?? "Unknown error"
                    self.users = []
                }
            }
        }
    }
}
